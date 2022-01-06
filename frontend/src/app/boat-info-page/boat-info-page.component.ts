import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Boat } from '../model/Boat';

@Component({
  selector: 'app-boat-info-page',
  templateUrl: './boat-info-page.component.html',
  styleUrls: ['./boat-info-page.component.css']
})
export class BoatInfoPageComponent implements OnInit {

  boat!: Boat;
  show = false;
  subscribeButtonText = "subscribe";

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
        let id = params['id'];
        this.http.get<any>('api/boat/' + id).subscribe(
          response => {
            this.boat = response;
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

  subscribeToBoat(boatId: number){
    var postData = {
      entity: "BOAT",
      idOfEntity: boatId
    }
    this.http.post("api/user/subscribe", postData).toPromise().then(data => {
    });
  }

}
