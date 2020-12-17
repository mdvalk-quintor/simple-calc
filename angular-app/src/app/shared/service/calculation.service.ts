import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Calculation} from '../model/calculation';
import {HttpUtil} from '../util/http-util';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable()
export class CalculationService {

  public static url = environment.apiUrl + '/calculations';

  constructor(private http: HttpClient) { }

  getCalculations(): Observable<Calculation[]> {
    return this.http.get<Calculation[]>(CalculationService.url, HttpUtil.getHeaders);
  }

  send(calculation: Calculation): Observable<Calculation> {
    return this.http.post<Calculation>(CalculationService.url, JSON.stringify(calculation), HttpUtil.getHeaders);
  }
}
