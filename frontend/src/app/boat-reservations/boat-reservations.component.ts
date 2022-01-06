import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BoatReservation } from '../model/BoatReservation';

@Component({
  selector: 'app-boat-reservations',
  templateUrl: './boat-reservations.component.html',
  styleUrls: ['./boat-reservations.component.css']
})
export class BoatReservationsComponent implements OnInit {

  boatReservations!: BoatReservation[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/boat/allreservations').subscribe(
      response => {
        this.boatReservations = response;
        console.log(this.boatReservations)
      }
    );
  }
}
