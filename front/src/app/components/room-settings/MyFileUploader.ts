
import {FileUploader} from "ng2-file-upload";
export class MyFileUploader extends FileUploader {

    constructor(
        roomId: number,
        private onSuccess: () => void) {
        super({
            url: `/room/${roomId}/image`,
            autoUpload: true,
            removeAfterUpload: true,
            disableMultipart: false,
        });
    }

    public onSuccessItem(item:any, response:any, status:any, headers:any):any {
        this.onSuccess();
        return {item, response, status, headers};
    }
}
