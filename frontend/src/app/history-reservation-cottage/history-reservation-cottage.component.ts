import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { CottageReservation } from '../model/CottageReservation';
import { User } from '../model/User';
import { WeekendCottageInfoPageComponent } from '../weekend-cottage-info-page/weekend-cottage-info-page.component';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-history-reservation-cottage',
  templateUrl: './history-reservation-cottage.component.html',
  styleUrls: ['./history-reservation-cottage.component.css']
})
export class HistoryReservationCottageComponent implements OnInit {

  cottageReservations!: CottageReservation[];
  id: any;
  coowner= false;
  reservation !: CottageReservation
  
  constructor(private http: HttpClient, private component: WeekendCottageInfoPageComponent) { }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage/cottagesReservation/'+ this.component.weekendCottage.id).subscribe(
      response => {
        this.cottageReservations = response;
        console.log(this.cottageReservations)
      }
    );
  }

  evoJe(res: any){
    this.reservation = res;
  }

}
