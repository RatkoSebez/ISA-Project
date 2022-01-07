import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AdventureReservation } from '../model/AdventureReservation';

@Component({
  selector: 'app-adventure-reservations',
  templateUrl: './adventure-reservations.component.html',
  styleUrls: ['./adventure-reservations.component.css']
})
export class AdventureReservationsComponent implements OnInit {

  adventureReservations!: AdventureReservation[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/adventure/allreservation').subscribe(
      response => {
        this.adventureReservations = response;
        console.log(this.adventureReservations)
      }
    );
  }

}
