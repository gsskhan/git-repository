import { NgModule } from '@angular/core';
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatMenuModule } from "@angular/material/menu";
import { MatIconModule } from "@angular/material/icon";

@NgModule({
  exports: [
    MatToolbarModule,
    MatMenuModule,
    MatIconModule
  ]
})
export class AppMaterialModule {}

/**
 * Created this module to keep Angular Material required Module imports in one place. - Gulam
 */