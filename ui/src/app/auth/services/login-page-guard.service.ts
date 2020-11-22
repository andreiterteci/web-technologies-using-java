import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginPageGuardService implements CanActivate {

  constructor(
    private _router: Router,
    private _auth: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this._auth.isAdminLoggedIn()) {
      this._router.navigate(['home']);
      return false;
    }
    return true;
  }

}
