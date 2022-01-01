import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Boat } from '../model/Boat';

@Component({
  selector: 'app-boat-card',
  templateUrl: './boat-card.component.html',
  styleUrls: ['./boat-card.component.css']
})
export class BoatCardComponent implements OnInit {

  boats!: Boat[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/boat').subscribe(
      response => {
        this.boats = response;
        //console.log(this.boats);
      }
    );
  }

}
