import { Component, OnInit, Input } from '@angular/core';
import { User } from "../model/user";
import { UserService } from "../service/user.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Input()
  isUserLoggedIn :boolean = false;

  @Input()
  user = new User;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onClickSubmit() {
    console.log("Submitted form data --> " + JSON.stringify(this.user));
    this.userService.getUserWithUsername(this.user.userName)
              .subscribe (u => {
                if (u.password === this.user.password && u.userName === this.user.userName ){
                  this.isUserLoggedIn = true;
                } else {
                  this.isUserLoggedIn = false;
                }
              });
  }

}
