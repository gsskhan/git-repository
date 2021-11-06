import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToolbarModule } from "primeng/toolbar";

/**
 * @author G S S KHAN
 * 
 * Application specific used PrimeNG modules.
 * 
 */


@NgModule({
  declarations: [],
  exports: [
    ToolbarModule
  ],
  imports: [
    CommonModule
  ]
})
export class PrimengModule { }
