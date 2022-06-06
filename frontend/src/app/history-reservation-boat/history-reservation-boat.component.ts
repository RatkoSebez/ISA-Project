import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventSettingsModel, View } from '@syncfusion/ej2-angular-schedule';
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
  reservation!: BoatReservation

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
    this.reservation = res
  }

  eventObject: EventSettingsModel = {
  }
  
  setView: View = 'Month'

  editReservation(){
    var start = formatDate(this.beginDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.finishDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      startDate: start,
      endDate: end,
      id: this.reservation.id
    }
    if(start && end){
      this.http.post("api/boat/editReservation", postData).toPromise().then(data => {
        if(!data){alert("Something went wrong, please try later")}
        window. location. reload();
      })
    }
  }

  doComment(){
    var e = (document.getElementById("commentSelect")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var CurValue = (<HTMLSelectElement><unknown>opt).value;
    var rol: number = +CurValue;

    var report = {
      clientEmail: this.reservation.clientEmail,
      endDate: this.reservation.endDate,
      shows : this.checkboxFlag,
      assessment : rol,
      comment: this.comment,
      entityId: this.reservation.boatId,
      entity: "BOAT"
    } 

    if(!this.reservation.canceled && this.comment){
      this.http.post("api/weekendCottage/mark", report).toPromise().then(data => {
        if(!data){alert("Something went wrong, please try later")}
        window. location. reload();
      })
    }else{alert("Add comment")}
  }

}
