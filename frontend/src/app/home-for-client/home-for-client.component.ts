import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { WeekendCottage } from '../model/WeekendCottage';

@Component({
  selector: 'app-home-for-client',
  templateUrl: './home-for-client.component.html',
  styleUrls: ['./home-for-client.component.css']
})
export class HomeForClientComponent implements OnInit {

  weekendCottages!: WeekendCottage[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage').subscribe(
      response => {
        this.weekendCottages = response;
        //console.log(this.weekendCottages)
      }
    );
  }

}
