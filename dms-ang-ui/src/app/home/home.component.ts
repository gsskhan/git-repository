import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user = new User;

  constructor() { }

  ngOnInit() {
  }

  onClickSubmit() {
    console.log("Entered details is : " + JSON.stringify(this.user));
 }

}
