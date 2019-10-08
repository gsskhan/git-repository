import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatInputModule } from '@angular/material/input'
import { FormsModule }   from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  }, 
  {
    path: 'home',
    component: HomeComponent
  }
];

@NgModule({
  declarations: [
    HomeComponent
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
