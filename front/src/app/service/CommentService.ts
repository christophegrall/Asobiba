
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {ChatMessage} from "../model/ChatMessage";
@Injectable()
export class CommentService {

    constructor(private http: Http) {}

    fetchComments(roomId: number): Observable<ChatMessage[]> {
        return this.http.get(`/api/comments/?roomId=${roomId}`)
            .map(it => it.json() as ChatMessage[]);
    }

    sendComment(roomId: number, userName: string, comment: string): Observable<ChatMessage> {
        let message = new ChatMessage(null, roomId, comment, userName, null);
        return this.http.post(`/api/comments`, message)
            .map(it => it.json() as ChatMessage);
    }
}
