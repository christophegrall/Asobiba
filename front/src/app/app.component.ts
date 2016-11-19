import {Component, ViewEncapsulation} from '@angular/core';

@Component({
    selector: 'app',
    templateUrl: './app.component.html',
    styleUrls: [
        './app.component.scss',
        'animate.css'
    ],
    encapsulation: ViewEncapsulation.None //cssをグローバルスコープで適用する
})
export class AppComponent {

    constructor() {}
}
