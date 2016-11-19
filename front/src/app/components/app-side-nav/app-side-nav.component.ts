import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
    selector: 'app-side-nav',
    templateUrl: './app-side-nav.component.html',
})
export class SideNavComponent implements OnInit {


    constructor(private router: Router) { }

    ngOnInit() {
    }
}
