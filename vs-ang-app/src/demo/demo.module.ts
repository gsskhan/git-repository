import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";

import { DemoComponent} from "./demo.component";

/* simple module */

@NgModule({
  declarations: [DemoComponent],
  imports: [BrowserModule, FormsModule],
  providers: [],
  bootstrap: [DemoComponent]
})
export class DemoModule{}