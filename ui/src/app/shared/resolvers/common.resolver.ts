import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CommonResolver implements Resolve<any> {

  constructor(private httpClient: HttpClient, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {

    const endpoint: string = route.data['endpoint'];

    const id = route.queryParams['id'] || route.queryParams['idToDuplicate'] || route.params['id'];

    if (id) {
      return this.httpClient.get<any>(`${endpoint}${id}`);
    }

    const currentState = this.router.getCurrentNavigation().extras.state;
    if (currentState && currentState.id) {
      return this.httpClient.get<any>(`${endpoint}${currentState.id}`);
    }

    return null as any;
  }
}
