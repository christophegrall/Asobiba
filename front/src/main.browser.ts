import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app';
import "materialize-css";
import "angular2-materialize";

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
