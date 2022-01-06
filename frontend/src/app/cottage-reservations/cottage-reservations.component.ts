import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CottageReservation } from '../model/CottageReservation';

@Component({
  selector: 'app-cottage-reservations',
  templateUrl: './cottage-reservations.component.html',
  styleUrls: ['./cottage-reservations.component.css']
})
export class CottageReservationsComponent implements OnInit {

  cottageReservations!: CottageReservation[];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage/allreservations').subscribe(
      response => {
        this.cottageReservations = response;
        console.log(this.cottageReservations)
      }
    );
  }

}
