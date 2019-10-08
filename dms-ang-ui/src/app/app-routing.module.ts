import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatInputModule } from '@angular/material/input'
import { FormsModule }   from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from "@angular/common";
import { HomeComponent } from "./home/home.component";

const routes: Routes = [ 
    { path: '', component: LoginRegisterComponent },
    { path: 'home', component: HomeComponent },
    { path: 'login-register', component: LoginRegisterComponent }
  ];

@NgModule({
  declarations: [
    HomeComponent,
    LoginRegisterComponent
  ],
  imports: [ 
      RouterModule.forRoot(routes),
      FormsModule,
      MatTabsModule,
      MatInputModule,
      MatButtonModule,
      CommonModule
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { 
  
}