import { formatDate } from '@angular/common';
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

  todayDate: Date = new Date();
  beginDate: any;
  startTime: any;
  finishDate: any;
  endTime: any;
  
  constructor(private http: HttpClient, private component: WeekendCottageInfoPageComponent) { }

  ngOnInit(): void {
    this.startTime= " 13:00";
    this.endTime= " 15:00";
    this.http.get<any>('api/weekendCottage/cottagesReservation/'+ this.component.weekendCottage.id).subscribe(
      response => {
        this.cottageReservations = response;
        console.log(this.cottageReservations)
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
      startDate: start + " " + this.startTime,
      endDate: end + " " + this.endTime,
      id: this.reservation.id
    }
    if(start && end){
      this.http.post("api/weekendCottage/editReservation", postData).toPromise().then(data => {
        if(!data){alert("Something went wrong, please try later")}
      })
    }

  }

}
