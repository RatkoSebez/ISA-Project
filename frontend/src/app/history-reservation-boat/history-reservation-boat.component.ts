import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BoatInfoPageComponent } from '../boat-info-page/boat-info-page.component';
import { Boat } from '../model/Boat';
import { BoatReservation } from '../model/BoatReservation';

@Component({
  selector: 'app-history-reservation-boat',
  templateUrl: './history-reservation-boat.component.html',
  styleUrls: ['./history-reservation-boat.component.css']
})
export class HistoryReservationBoatComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private component: BoatInfoPageComponent) { }

  boatReservations!: BoatReservation[]
  boat!: Boat

  todayDate: Date = new Date();
  beginDate: any;
  finishDate: any;
  checkboxFlag: any;
  comment: any;

  ngOnInit(): void {
    this.http.get<any>('api/boat/boatReservations/'+ this.component.boat.id).subscribe(
      response => {
        this.boatReservations = response;
      }
      
    );
  }

  getId(res: any){
    this.boat = res
  }

  editReservation(){
    var start = formatDate(this.beginDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.finishDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      startDate: start,
      endDate: end,
      id: this.boat.id
    }
    if(start && end){
      this.http.post("api/boat/editReservation", postData).toPromise().then(data => {
        if(!data){alert("Something went wrong, please try later")}
        window. location. reload();
      })
    }

  }

}
