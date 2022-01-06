import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeekendCottage } from '../model/WeekendCottage';

@Component({
  selector: 'app-weekend-cottage-info-page',
  templateUrl: './weekend-cottage-info-page.component.html',
  styleUrls: ['./weekend-cottage-info-page.component.css']
})
export class WeekendCottageInfoPageComponent implements OnInit {

  weekendCottage!: WeekendCottage;
  show = false;

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
    this.http.get('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
      }
    });
  }

  subscribeToCottage(cottageId: number){
    var postData = {
      entity: "WEEKEND_COTTAGE",
      idOfEntity: cottageId
    }
    this.http.post("api/user/subscribe", postData).toPromise().then(data => {
    });
  }
}
