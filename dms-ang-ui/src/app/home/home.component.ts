import { Component, OnInit, Input } from '@angular/core';
import { User } from "../model/user";
import { UserService } from "../service/user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loginStatus: Boolean;

  @Input()
  user = new User;
  userRecord = new User;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onClickSubmit() {
    console.log("Submitted details is --> " + JSON.stringify(this.user));
    this.userService.getUserWithUsername(this.user.userName)
              .subscribe (u => this.userRecord = u);

    console.log ("check DB -->" , this.userRecord.userName , this.userRecord.password , this.userRecord.userId )
    console.log ("check UI -->" , this.user.userName , this.user.password )
  }

}
