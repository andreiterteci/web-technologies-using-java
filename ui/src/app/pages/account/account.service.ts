import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RestResponseModel} from "../../shared/models/RestResponseModel";
import {FitnessModel} from "../../shared/models/Fitness";
import {AccountModel} from "../../shared/models/User";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  url: string = environment.url + 'api/account/';

  constructor(private httpClient: HttpClient) {
  }

  getCurrent(): Observable<AccountModel> {
    return this.httpClient.get<AccountModel>(this.url);
  }

  update(accountModel: AccountModel): Observable<RestResponseModel> {
    return this.httpClient.post<RestResponseModel>(this.url, accountModel);
  }
}
