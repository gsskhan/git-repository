import { Component, OnInit, Input } from '@angular/core';
import { User } from "../../model/user";
import { UserService } from "../../service/user.service";
import { Router } from "@angular/router";
import { MatSnackBar } from '@angular/material/snack-bar';

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

  @Input()
  newuser = new User;

  @Input()
  rolesArray: string[] = ['SYSADMIN','APPROVER','REVIEWER','PUBLISHER'];

  constructor(private userService: UserService
    , private _router: Router
    , private _snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  authenticateLogin() {
    console.log("Submitted form data --> " + JSON.stringify(this.user));
    this.userService
      .getUserWithUsername(this.user.userName)
      .subscribe(u => {
        console.log("Recieved from service --> " , JSON.stringify(u));
        if (u.password === this.user.password && u.userName === this.user.userName && u.role === this.user.role) {
          this.isUserLoggedIn = true;
          this._router.navigate(['/home']);
          this.displayMessage("Login successful. Welcome! " + u.firstName );
        } else {
          this.isUserLoggedIn = false;
          this._router.navigate(['']);
          this.displayMessage("Login failed. Please check username/password/role.");
        }
      });
  }

  displayMessage(message: string){
    this._snackBar.open(message , 'Dismiss', {
      duration : 5000
    });
  }

  registerNewUser(){
    console.log("Submitted form data --> " + JSON.stringify(this.newuser));
  }

}
