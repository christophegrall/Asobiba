
import {Component, OnInit, ViewChild, NgZone, OnDestroy} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../../../service/RoomService";
import {Room} from "../../../model/Room";
import {ChatMessage} from "../../../model/ChatMessage";
import {ChatComponent} from "../../chat/app-chat.component";


declare var EventSource : sse.IEventSourceStatic;

declare module sse {

    enum ReadyState {CONNECTING = 0, OPEN = 1, CLOSED = 2}

    interface IEventSourceStatic extends EventTarget {
        new (url: string, eventSourceInitDict?: IEventSourceInit);
        url: string;
        withCredentials: boolean;
        CONNECTING: ReadyState; // constant, always 0
        OPEN: ReadyState; // constant, always 1
        CLOSED: ReadyState; // constant, always 2
        readyState: ReadyState;
        onopen: Function;
        onmessage: (event: IOnMessageEvent) => void;
        onerror: Function;
        close: () => void;
    }

    interface IEventSourceInit {
        withCredentials?: boolean;
    }

    interface IOnMessageEvent {
        data: string;
    }
}


@Component({
    selector: 'app-room-page',
    templateUrl: './app-room-page.component.html'
})
export class RoomPageComponent implements OnInit, OnDestroy {
    private room: Room;

    private source: sse.IEventSourceStatic;
    @ViewChild(ChatComponent)
    chatComponent: ChatComponent;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private roomService: RoomService,
        private _ngZone: NgZone
    ){}

    ngOnInit(): void {

        this.route.params.forEach(params => {
            let roomId = params['id'];
            this.roomService.fetchRoom(roomId).subscribe(it => this.room = it);

            this.source = new EventSource(`/api/comments/${roomId}/stream`);
            this.source.onmessage = event => {
                this._ngZone.run(() => {
                    let message = JSON.parse(event.data);
                    if(message.type == 'ROOM_UPDATE') {
                        this.onUpdateRoom(message.data as Room);
                    } else if(message.type == 'ROOM_DELETE') {
                        this.onDeleteRoom(message.data as Room);
                    } else if(message.type == 'COMMENT_CREATE') {
                        this.onCreateComment(message.data as ChatMessage);
                    }
                });
            };
        });
    }

    ngOnDestroy(): void {
        this.source.close();
        console.log('room sse closed.');
    }

    onUpdateRoom(room: Room) {

    }

    onDeleteRoom(room: Room) {
        this.router.navigateByUrl('/');
    }

    onCreateComment(chat: ChatMessage) {
        this.chatComponent.onCreateComment(chat);
    }

}
