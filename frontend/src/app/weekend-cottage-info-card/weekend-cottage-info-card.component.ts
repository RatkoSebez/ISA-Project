import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeekendCottage } from '../model/WeekendCottage';

@Component({
  selector: 'app-weekend-cottage-info-card',
  templateUrl: './weekend-cottage-info-card.component.html',
  styleUrls: ['./weekend-cottage-info-card.component.css']
})
export class WeekendCottageInfoCardComponent implements OnInit {

  weekendCottage!: WeekendCottage;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
        let id = params['id'];
        this.http.get<any>('api/weekendCottage/' + id).subscribe(
          response => {
            this.weekendCottage = response;
            // console.log(this.weekendCottage);
          }
        );
    });
  }
}
