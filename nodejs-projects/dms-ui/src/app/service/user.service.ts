import { Injectable } from '@angular/core';
import { User } from "../model/user";
import { HttpClient } from "@angular/common/http";
import { Endpoints } from "../common/endpoints";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url :string;

  constructor(protected http: HttpClient) { }

  public getUserWithUsername ( uname: String) : Observable<User> {
    this.url = Endpoints.FIND_USER_GET + "/" + uname;
    console.log("Initiated REST GET Call --> ", this.url);
    return this.http.get<User>(Endpoints.FIND_USER_GET + "/" + uname);
  } 

}