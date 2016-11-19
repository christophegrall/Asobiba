
import {Component, Input} from "@angular/core";
import {Room} from "../../model/Room";
import {Router} from "@angular/router";
@Component({
    selector: 'app-room-card',
    templateUrl: './app-room-card.component.html'
})
export class RoomCardComponent {

    @Input()
    public roomInfo: Room = null;
    private timestamp: string = `?time=${new Date().getTime()}`;

    constructor(private router: Router) {

    }

    onClick() {
        if (this.roomInfo) {
            this.router.navigateByUrl(`/room/${this.roomInfo.roomId}`);
        }
    }

    updateImage() {
        this.timestamp = `?time=${new Date().getTime()}`;
    }
}
