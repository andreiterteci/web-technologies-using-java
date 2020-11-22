import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {UserLoginData} from "../../shared/models/User";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private router: Router) {
  }

  signOut() {
    window.sessionStorage.clear();
    this.router.navigate(['login']);
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken() {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): UserLoginData {
    const key = sessionStorage.getItem(USER_KEY);
    return key != null ? JSON.parse(key) : null;
  }
}
