import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FastReservation } from '../model/FastReservation';

@Component({
  selector: 'app-fast-reservation-cottage',
  templateUrl: './fast-reservation-cottage.component.html',
  styleUrls: ['./fast-reservation-cottage.component.css']
})
export class FastReservationCottageComponent implements OnInit {

  fastReservations!: FastReservation[];

  public constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get<any>('api/fastReservation/cottage').subscribe(
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
    this.http.post("api/weekendCottage/reservation", postData).toPromise().then(data => {
      console.log(data);
    });
    this.router.navigate(['/cottageReservations']);
  }

}
