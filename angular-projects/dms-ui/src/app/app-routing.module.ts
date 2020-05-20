import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from "@angular/common";

import { MatTabsModule } from '@angular/material/tabs';
import { MatInputModule } from '@angular/material/input'
import { FormsModule }   from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';

import { HomeComponent } from "./component/home/home.component";
import { LoginRegisterComponent } from './component/login-register/login-register.component';

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
      MatSnackBarModule,
      MatSelectModule,
      CommonModule
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { 
  
}