import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { DemoModule } from "./demo/demo.module";

if (environment.production) {
  enableProdMode();
}

/* commented default code - gulam 
platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.log(err));
*/
platformBrowserDynamic().bootstrapModule(DemoModule)
.catch(err => console.log(err));
