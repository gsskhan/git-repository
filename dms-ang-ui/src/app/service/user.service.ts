import { Injectable } from '@angular/core';
import { User } from "../model/user";
import { HttpClient } from "@angular/common/http";
import { Endpoints } from "../common/endpoints";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user = new User; 

  constructor(private http: HttpClient) { }

  userLoginAuthentication(data: User) {
    console.log("Recieved Data in userLoginAuthentication() : " + JSON.stringify(data))

    this.http.get(Endpoints.FIND_USER_GET + "/" + data.userName)
      .subscribe((res : User ) =>  {
        console.log( "GET Response:" + JSON.stringify(res) )
        this.user = res;
      });

    console.log("check -->" + this.user.password);
    console.log("check -->" + this.user.userName);

    if (data.password == this.user.password && data.userName == this.user.userName) {
      console.log("valid user");
      return true;
    } else {
      console.log("in-valid user");
      return false;
    }
  }

}