
import {Component, OnInit, Input, OnChanges, SimpleChanges} from "@angular/core";
import {ChatMessage} from "../../model/ChatMessage";
import {CommentService} from "../../service/CommentService";
import {Room} from "../../model/Room";
@Component({
    selector: 'app-chat',
    templateUrl: './app-chat.component.html'
})
export class ChatComponent implements OnInit {
    private messages: ChatMessage[] = [];

    @Input()
    private room: Room;

    private message: string = '';

    constructor(
        private commentService: CommentService
    ) {}

    ngOnInit(): void {
        this.commentService.fetchComments(this.room.roomId)
            .subscribe(it => {
                this.messages = it;
            });
    }

    onCreateComment(chat: ChatMessage) {
        let copy = this.messages.map((it: ChatMessage) =>
            new ChatMessage(it.commentId, it.roomId, it.comment, it.userName, it.commentedAt));
        copy.push(chat);
        this.messages = copy;
    }

    send() {
        this.commentService.sendComment(this.room.roomId, 'test', this.message).subscribe(it => {});
        this.message = '';
    }

}
