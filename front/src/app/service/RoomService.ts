
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import 'rxjs/Rx';
import {Room} from "../model/Room";

@Injectable()
export class RoomService {

    constructor(private http: Http) {}

    fetchRooms(): Observable<Room[]> {
        return this.http.get('/api/rooms')
            .map(it => it.json() as Room[]);
    }

    fetchRoom(id: number): Observable<Room> {
        return this.http.get(`/api/rooms/${id}`)
            .map(it => it.json() as Room);
    }

    createRoom(title: string, roomName: string): Observable<Room> {
        let room = new Room(null, title, roomName);
        return this.http.post('/api/rooms', room)
            .map(it => it.json() as Room);
    }

    deleteRoom(id: number): Observable<Room> {
        return this.http.delete(`/api/rooms/${id}`)
            .map(it => it.json() as Room);
    }

    updateRoom(room: Room): Observable<Room> {
        return this.http.put('/api/rooms', room).map(it => it.json() as Room);
    }
}
