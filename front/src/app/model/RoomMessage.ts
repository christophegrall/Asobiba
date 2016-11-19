
import {Room} from "./Room";
export class RoomMessage {
    constructor(
        public type: string,
        public data: Room,
    ) {}
}
