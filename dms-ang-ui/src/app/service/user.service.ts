import { Injectable } from '@angular/core';
import { User } from "../model/user";
import { HttpClient } from "@angular/common/http";
import { Endpoints } from "../common/endpoints";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(protected http: HttpClient) { }

  public getUserWithUsername ( uname: String) : Observable<User> {
    return this.http.get<User>(Endpoints.FIND_USER_GET + "/" + uname);
  } 

}