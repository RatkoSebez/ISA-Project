import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { weekendCottage } from '../model/WeekendCottage';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  weekendCottages!: weekendCottage[];

  constructor(private http: HttpClient) { }

  getWeekendCottages(){
    return this.http.get('api/weekendCottage').subscribe();
  }
}
