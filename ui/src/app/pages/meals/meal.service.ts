import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MealModel} from "../../shared/models/Meal";
import {RestResponseModel} from "../../shared/models/RestResponseModel";

@Injectable({
  providedIn: 'root'
})
export class MealService {

  url: string = environment.url + 'api/meal/';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<MealModel[]> {
    return this.httpClient.get<MealModel[]>(this.url);
  }

  create(mealModel: MealModel): Observable<RestResponseModel> {
    return this.httpClient.post<RestResponseModel>(this.url, mealModel);
  }

  delete(id: string): Observable<RestResponseModel> {
    return this.httpClient.delete<RestResponseModel>(this.url + id);
  }

  update(mealModel: MealModel): Observable<RestResponseModel> {
    return this.httpClient.put<RestResponseModel>(this.url, mealModel);
  }
}
