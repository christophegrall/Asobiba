import { RouterModule, Routes } from '@angular/router';
import {LobbyPageComponent} from "./components/page/app-lobby-page/app-lobby-page.component";
import {RoomPageComponent} from "./components/page/app-room-page/app-room-page.component";

const routerConfig: Routes = [
    { path: '', component: LobbyPageComponent},
    { path: 'room/:id', component: RoomPageComponent},
];

export const routes = RouterModule.forRoot(routerConfig, { useHash: true });
