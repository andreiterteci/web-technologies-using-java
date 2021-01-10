import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HealthModel} from "../../shared/models/Health";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  url: string = environment.url + 'api/home/';

  constructor(private httpClient: HttpClient) {
  }

  getHealthScore(): Observable<HealthModel> {
    return this.httpClient.get<HealthModel>(this.url + "healthScore");
  }
}
