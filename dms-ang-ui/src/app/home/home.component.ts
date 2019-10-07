import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";
import { UserService } from "../service/user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loginStatus: Boolean;
  user = new User;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onClickSubmit() {
    console.log("Submitted details is : " + JSON.stringify(this.user));
    this.loginStatus = this.userService.userLoginAuthentication(this.user);
  }

}
