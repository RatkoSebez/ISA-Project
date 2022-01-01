import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from '../model/Boat';

@Component({
  selector: 'app-boat-info-card',
  templateUrl: './boat-info-card.component.html',
  styleUrls: ['./boat-info-card.component.css']
})
export class BoatInfoCardComponent implements OnInit {

  boat!: Boat;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
        let id = params['id'];
        this.http.get<any>('api/boat/' + id).subscribe(
          response => {
            this.boat = response;
          }
        );
    });
  }

}