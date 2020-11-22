import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";
import {environment} from "../../../environments/environment";

const AUTH_API = environment.url;
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class AuthService {

  email: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private tokenStorageService: TokenStorageService
  ) {
  }

  login(credentials: any): Observable<any> {
    return this.http.post(AUTH_API + 'api/auth/signin', {
      email: credentials.email,
      password: credentials.password
    }, httpOptions);
  }

  register(user: any): Observable<any> {
    return this.http.post(AUTH_API + 'api/auth/signup', user, httpOptions);
  }

  isAdminLoggedIn(): boolean {
    const token = this.tokenStorageService.getToken();
    return Boolean(token);
  }
}
