import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "./auth.service";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private _router: Router,
    private _auth: AuthService,
    private tokenStorageService: TokenStorageService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.tokenStorageService.getUser();
    if (route.data.allowedRoles && route.data.allowedRoles != "") {
      currentUser.roles.forEach((role: any) => {
        if (route.data.allowedRoles.includes(role)) {
          return true;
        } else {
          this._router.navigate(['']);
          return false;
        }
      });
    }
    if (this._auth.isAdminLoggedIn()) {
      return true;
    }
    this._router.navigate(['login']);
    return false;
  }
}
