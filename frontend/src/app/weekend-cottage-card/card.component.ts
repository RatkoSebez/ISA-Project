import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { WeekendCottage } from '../model/WeekendCottage';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  weekendCottages!: WeekendCottage[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage').subscribe(
      response => {
        this.weekendCottages = response;
        console.log(this.weekendCottages)
      }
    );
  }

}
