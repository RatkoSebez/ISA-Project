import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { weekendCottage } from '../model/WeekendCottage';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  weekendCottages!: weekendCottage[];

  constructor(private apiService: ApiService, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage').subscribe(
      response => {
        this.weekendCottages = response;
        console.log(this.weekendCottages)
      }
    );
    // this.weekendCottages = this.apiService.getWeekendCottages();
  }

}
