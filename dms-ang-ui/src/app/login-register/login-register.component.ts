import { Component, OnInit, Input } from '@angular/core';
import { User } from "../model/user";
import { UserService } from "../service/user.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.css']
})
export class LoginRegisterComponent implements OnInit {

  @Input()
  isUserLoggedIn: boolean = false;

  @Input()
  user = new User;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  authenticateLogin() {
    console.log("Submitted form data --> " + JSON.stringify(this.user));
    this.userService
      .getUserWithUsername(this.user.userName)
      .subscribe(u => {
        console.log("Recieved from service --> " , JSON.stringify(u));
        if (u.password === this.user.password && u.userName === this.user.userName) {
          this.isUserLoggedIn = true;
          this.router.navigate(['/home']);
        } else {
          this.isUserLoggedIn = false;
          this.router.navigate(['']);
        }
      });
  }

}
