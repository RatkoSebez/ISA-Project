import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventSettingsModel, View } from '@syncfusion/ej2-angular-schedule';
import { now } from 'moment';
import { calendar } from '../model/calendar';
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

  // Mapa
  latitude = 45.2396;
  longitude = 19.8227;
  lon!: any
  lat!: any

  cottageReservations!: CottageReservation[];
  lista!: calendar[];
  newList!: calendar[];
  id: any;
  coowner= false;
  reservation !: CottageReservation

  todayDate: Date = new Date();
  beginDate: any;
  finishDate: any;
  checkboxFlag: any;
  comment: any;
  
  constructor(private http: HttpClient, private router: Router, private component: WeekendCottageInfoPageComponent) { }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage/cottagesReservation/'+ this.component.weekendCottage.id).subscribe(
      response => {
        this.cottageReservations = response;
        this.lista = response;
        console.log(this.cottageReservations)
      }
      
    );

    this.lon = this.component.weekendCottage.longitude;
    this.lat = this.component.weekendCottage.latitude;

    this.http.get<any>('api/weekendCottage/reservationc').subscribe(
      response => {
        this.lista = response;
      }
    );
  }

  getId(res: any){
    this.reservation = res
  }

  editReservation(){
    var start = formatDate(this.beginDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.finishDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      startDate: start,
      endDate: end,
      id: this.reservation.id
    }
    if(start && end){
      this.http.post("api/weekendCottage/editReservation", postData).toPromise().then(data => {
        if(!data){alert("Something went wrong, please try later")}
        window. location. reload();
      })
    }

  }

  eventObject: EventSettingsModel = {
    dataSource: this.lista
  }
  
  setView: View = 'Month'

  doComment(){
    var e = (document.getElementById("commentSelect")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var CurValue = (<HTMLSelectElement><unknown>opt).value;
    var rol: number = +CurValue;

    var report = {
      clientEmail: this.reservation.clientEmail,
      cottageId: this.reservation.cottageId,
      endDate: this.reservation.endDate,
      shows : this.checkboxFlag,
      assessment : rol,
      comment: this.comment,
      entityId: this.reservation.cottageId,
      entity: "WEEKEND_COTTAGE"
    } 
    
    if(!this.reservation.canceled && this.comment){
      this.http.post("api/weekendCottage/mark", report).toPromise().then(data => {
        if(!data){alert("Something went wrong, please try later")}
        window. location. reload();
      })
    }else{alert("Add comment")}
  }

}
