import { Injectable } from '@angular/core';
import { User } from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  userLoginAuthentication (data: User ) {
    if (data.password == 'password' && data.userName == 'tester1') {
      console.log("valid user");
      return true;
    } else {
      console.log("in-valid user");
      return false;
    }    
  }

}
