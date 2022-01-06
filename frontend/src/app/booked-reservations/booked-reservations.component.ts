import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { BoatReservation } from '../model/BoatReservation';

@Component({
  selector: 'app-booked-reservations',
  templateUrl: './booked-reservations.component.html',
  styleUrls: ['./booked-reservations.component.css']
})
export class BookedReservationsComponent implements OnInit {

  boatReservations!: BoatReservation[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/boat/reservation').subscribe(
      response => {
        this.boatReservations = response;
        console.log(this.boatReservations)
      }
    );
  }

  cancelReservation(id: number){
    this.http.delete('api/boat/reservation/' + id)
      .subscribe();
  }
}
