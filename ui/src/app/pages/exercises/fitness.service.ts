import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RestResponseModel} from "../../shared/models/RestResponseModel";
import {FitnessModel} from "../../shared/models/Fitness";

@Injectable({
  providedIn: 'root'
})
export class FitnessService {

  url: string = environment.url + 'api/fitness/';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<FitnessModel[]> {
    return this.httpClient.get<FitnessModel[]>(this.url);
  }

  create(fitnessModel: FitnessModel): Observable<RestResponseModel> {
    return this.httpClient.post<RestResponseModel>(this.url, fitnessModel);
  }

  delete(id: string): Observable<RestResponseModel> {
    return this.httpClient.delete<RestResponseModel>(this.url + id);
  }

  update(fitnessModel: FitnessModel): Observable<RestResponseModel> {
    return this.httpClient.put<RestResponseModel>(this.url, fitnessModel);
  }
}
