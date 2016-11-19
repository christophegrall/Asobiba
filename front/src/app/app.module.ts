
import "materialize-css";
import {MaterializeModule} from "angular2-materialize";

import { NgModule } from '@angular/core'
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';

import {
    AppComponent,
    routes
} from './';
import {CommonModule, HashLocationStrategy, LocationStrategy} from "@angular/common";
import {LobbyPageComponent} from "./components/page/app-lobby-page/app-lobby-page.component";
import {SideNavComponent} from "./components/app-side-nav/app-side-nav.component";
import {RoomCardComponent} from "./components/room-card/app-room-card.component";
import {RoomService} from "./service/RoomService";
import {RoomPageComponent} from "./components/page/app-room-page/app-room-page.component";
import {RoomSettingsComponent} from "./components/room-settings/app-room-settings.component";
import {ChatComponent} from "./components/chat/app-chat.component";
import {CommentService} from "./service/CommentService";

@NgModule({
    imports: [
        routes,
        BrowserModule,
        HttpModule,
        MaterializeModule,
        CommonModule,
        FormsModule,
    ],
    providers: [
        RoomService,
        CommentService,
        { provide: LocationStrategy, useClass: HashLocationStrategy },
    ],
    declarations: [
        FileSelectDirective,
        AppComponent,
        LobbyPageComponent,
        SideNavComponent,
        RoomCardComponent,
        RoomPageComponent,
        RoomSettingsComponent,
        ChatComponent
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
