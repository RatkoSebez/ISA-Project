import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AdventureReservation } from '../model/AdventureReservation';
import { BoatReservation } from '../model/BoatReservation';
import { CottageReservation } from '../model/CottageReservation';

@Component({
  selector: 'app-booked-reservations',
  templateUrl: './booked-reservations.component.html',
  styleUrls: ['./booked-reservations.component.css']
})
export class BookedReservationsComponent implements OnInit {

  boatReservations!: BoatReservation[];
  cottageReservations!: CottageReservation[];
  adventureReservations!: AdventureReservation[];

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get<any>('api/boat/reservation').subscribe(
      response => {
        this.boatReservations = response;
        console.log(this.boatReservations)
      }
    );
    this.http.get<any>('api/weekendCottage/reservation').subscribe(
      response => {
        this.cottageReservations = response;
        console.log(this.cottageReservations)
      }
    );
    this.http.get<any>('api/adventure/reservation').subscribe(
      response => {
        this.adventureReservations = response;
        console.log(this.adventureReservations)
      }
    );
  }

  cancelBoatReservation(id: number){
    this.http.delete('api/boat/reservation/' + id)
      .subscribe(() => {
        this.router.navigate(['/boatReservations']);
      });
  }

  cancelCottageReservation(id: number){
    this.http.delete('api/weekendCottage/reservation/' + id)
      .subscribe(() => {
        this.router.navigate(['/cottageReservations']);
      });
  }

  cancelAdventureReservation(id: number){
    this.http.delete('api/adventure/reservation/' + id)
      .subscribe(() => {
        this.router.navigate(['/adventureReservations']);
      });
  }
}
