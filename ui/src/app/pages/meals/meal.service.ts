import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MealModel} from "../../shared/models/Meal";

@Injectable({
  providedIn: 'root'
})
export class MealService {

  url: string = environment.url + 'api/meal';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<MealModel[]> {
    return this.httpClient.get<MealModel[]>(this.url);
  }
}
