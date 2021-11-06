import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { PrimengModule } from "./primeng/primeng.module";
import { HeaderComponent } from './header/header.component';
import { LoginRegisterComponent } from './login-register/login-register.component'; 

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginRegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    PrimengModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
