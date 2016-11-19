import {Component, EventEmitter, OnInit, OnDestroy, NgZone, ViewChildren, QueryList} from "@angular/core"
import {Room} from "../../../model/Room";
import {RoomService} from "../../../service/RoomService";
import {MaterializeAction} from 'angular2-materialize';
import {ROOM_NAMES} from "../../../model/RoomNames";
import {RoomMessage} from "../../../model/RoomMessage";
import {Router} from "@angular/router";
import {RoomCardComponent} from "../../room-card/app-room-card.component";


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
    selector: 'app-lobby-page',
    templateUrl: './app-lobby-page.component.html'
})
export class LobbyPageComponent implements OnInit, OnDestroy {
    private rooms: Room[] = null;
    private modalActions = new EventEmitter<any>();

    private allRooms = ROOM_NAMES;
    private title: string = '';
    private roomName: string = '';

    @ViewChildren(RoomCardComponent)
    private cards: QueryList<RoomCardComponent>;

    private source: sse.IEventSourceStatic;

    constructor(
        private roomService: RoomService,
        private router: Router,
        private _ngZone: NgZone) {
        roomService.fetchRooms().subscribe(it => this.rooms = it);
    }
    ngOnInit(): void {
        this.source = new EventSource('/api/rooms/stream');
        this.source.onmessage = event => {
            this._ngZone.run(() => {
                let message = JSON.parse(event.data) as RoomMessage;
                if(message.type == 'ROOM_CREATE') {
                    this.onCreateRoom(message.data);
                } else if(message.type == 'ROOM_UPDATE') {
                    this.onUpdateRoom(message.data);
                } else if(message.type == 'ROOM_DELETE') {
                    this.onDeleteRoom(message.data);
                } else if(message.type == 'ROOM_IMAGE_UPDATE') {
                    this.cards.forEach(it => it.updateImage());
                }
            });
         };
    }

    ngOnDestroy(): void {
        this.source.close();
        console.log('lobby sse closed.');
    }

    onCreateRoom(room: Room) {
        this.rooms.push(room);
    }

    onUpdateRoom(room: Room) {
        let target = this.rooms
            .find(it => it.roomId == room.roomId);
        target.roomName = room.roomName;
        target.title = room.title;
    }

    onDeleteRoom(room: Room) {
        this.rooms = this.rooms
            .filter(it => it.roomId != room.roomId);
    }

    openModal() {
        this.modalActions.emit({action:"modal",params:['open']});
    }
    closeModal() {
        this.modalActions.emit({action:"modal",params:['close']});
    }

    selectFreeRooms(rooms: Room[]): string[] {
        return this.allRooms
            .filter(it => rooms.find(room => room.roomName == it) == null);
    }

    onSubmit() {
        this.roomService.createRoom(this.title, this.roomName).subscribe(
            it => {
                this.closeModal();
                this.title = '';
                this.roomName = '';
                this.router.navigateByUrl(`/room/${it.roomId}`);
            });
    }
}
