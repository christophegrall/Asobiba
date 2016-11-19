
import {Component, Input, OnInit, EventEmitter} from "@angular/core";
import {Room} from "../../model/Room";
import {RoomService} from "../../service/RoomService";
import {FileUploader} from "ng2-file-upload";
import {MyFileUploader} from "./MyFileUploader";
@Component({
    selector: 'app-room-settings',
    templateUrl: './app-room-settings.component.html'
})
export class RoomSettingsComponent implements OnInit {
    @Input()
    private room: Room;
    private uploader: FileUploader;
    private modalActions = new EventEmitter<any>();

    constructor(private roomService: RoomService) {
    }

    ngOnInit(): void {
        this.uploader = new MyFileUploader(
            this.room.roomId, () => {
                console.log('画像アップロード完了');
            });
    }

    updateRoomTitle() {
        this.roomService.updateRoom(this.room).subscribe(it =>{});
    }

    openModal() {
        this.modalActions.emit({action:"modal",params:['open']});
    }
    closeModal() {
        this.modalActions.emit({action:"modal",params:['close']});
    }

    deleteRoom() {
        this.roomService.deleteRoom(this.room.roomId).subscribe(it => {});
    }
}
