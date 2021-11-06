import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToolbarModule } from "primeng/toolbar";
import { ButtonModule } from "primeng/button";

/**
 * @author G S S KHAN
 * 
 * Application specific used PrimeNG modules.
 * 
 */


@NgModule({
  declarations: [],
  exports: [
    ToolbarModule,
    ButtonModule
  ],
  imports: [
    CommonModule
  ]
})
export class PrimengModule { }
