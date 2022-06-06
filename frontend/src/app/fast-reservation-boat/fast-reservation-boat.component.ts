import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { AvailableReservations } from '../model/AvailableReservations';
import { FastReservation } from '../model/FastReservation';

@Component({
  selector: 'app-fast-reservation-boat',
  templateUrl: './fast-reservation-boat.component.html',
  styleUrls: ['./fast-reservation-boat.component.css']
})
export class FastReservationBoatComponent implements OnInit {

  fastReservations!: AvailableReservations[];

  public constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get<any>('api/boat/fastreservation').subscribe(
      response => {
        this.fastReservations = response;
      }
    );
  }

  public makeReservation(startDate: Date, endDate: Date, price: number, id: number){
    var postData = {
      startDate: startDate,
      endDate: endDate,
      boatId: id,
      price: price
    }
    console.log(startDate)
    this.http.post("api/boat/reservation", postData).toPromise().then(data => {
      console.log(data);
    });
    this.router.navigate(['/boatReservations']);
  }

}
