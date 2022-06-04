import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { throwIfEmpty } from 'rxjs';
import { HistoryReservationCottageComponent } from '../history-reservation-cottage/history-reservation-cottage.component';
import { User } from '../model/User';
import { WeekendCottage } from '../model/WeekendCottage';
import { DatePipe, formatDate } from '@angular/common'

@Component({
  selector: 'app-weekend-cottage-info-page',
  templateUrl: './weekend-cottage-info-page.component.html',
  styleUrls: ['./weekend-cottage-info-page.component.css']
})
export class WeekendCottageInfoPageComponent implements OnInit {

  weekendCottage!: WeekendCottage;
  user = false;
  coowner = false;
  edit = true;
  postCottage! : WeekendCottage;
  id: any

  //Fast availably
  todayDate: Date = new Date();
  firstDate: any;
  lastDate: any;
  newActionExpirationDate: any;
  actionprice: any;
  currprice: any;

  //Normal availably
  beginDate: any;
  startTime: any;
  finishDate: any;
  endTime: any;

  public name = null;
  public address = null
  public description = null
  public rules = null
  public pricelist = null
  public additionalservices = null
  selectedFile = null;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.startTime = "14:00"
    this.endTime = "12:00"
    this.activatedRoute.queryParams.subscribe(params => {
        this.id = params['id'];
        this.http.get<WeekendCottage>('api/weekendCottage/' + this.id).subscribe(
          response => {
            this.weekendCottage = response;
            this.currprice = this.weekendCottage.priceList
            this.postCottage = this.weekendCottage
          }
        );
    });
    this.http.get<User>('api/user').subscribe(val => {
      console.log(val);
      if(val.role === "ROLE_CLIENT") {
        this.user = true;
      }else if(val.role ==="ROLE_WEEKENDCOTTOWNER"){
        if(this.weekendCottage.cottageOwnerId === val.id){this.coowner = true;}
      }
    });
  }

  doEdit(){
    this.edit = !this.edit;
  }

  selectImage(event: any){
    this.selectedFile = event.target.files[0].name;
  }

  subscribeToCottage(cottageId: number){
    var postData = {
      entity: "WEEKEND_COTTAGE",
      idOfEntity: cottageId
    }
    this.http.post("api/user/subscribe", postData).toPromise().then(data => {
    });
  }

  doDeleteCottage(){
    this.http.delete('api/weekendCottage/cottage/'+ this.postCottage.id).toPromise().then(data => {
      this.router.navigate(['/mycottages']);
    });
  }

  doEditCottage(){
    if(this.name){this.postCottage.name = this.name}
    if(this.address){this.postCottage.address = this.address}
    if(this.description){this.postCottage.description = this.description}
    if(this.rules){this.postCottage.rulesOfConduct = this.rules}
    if(this.selectedFile){this.postCottage.image = this.selectedFile}
    if(this.pricelist){this.postCottage.priceList = this.pricelist}
    if(this.additionalservices){this.postCottage.additionalServices = this.additionalservices}

    this.http.post('api/weekendCottage/editCottage', this.postCottage).toPromise().then(data => {
      this.router.navigate(['/mycottages']);
    });
  }

  addReservation(){
    var start = formatDate(this.firstDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.lastDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      entity: "WEEKEND_COTTAGE",
      entityId: this.weekendCottage.id,
      startDate: start + " " + this.startTime,
      oldPrice: this.currprice,
      endDate: end + " " + this.endTime,
      expirationDate: this.newActionExpirationDate,
      newPrice: this.actionprice,
      fast: true
    }

    this.http.post("api/weekendCottage/availability", postData).toPromise().then(data => {
      if(!data){alert("Cottage alredy busy")}
      else{this.router.navigate(['/mycottages']);}
    });
  }

  editAvailability(){
    var start = formatDate(this.beginDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.finishDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      entity: "WEEKEND_COTTAGE",
      entityId: this.weekendCottage.id,
      startDate: start + " " + this.startTime,
      endDate: end + " " + this.endTime,
      fast: false
    }

    this.http.post("api/weekendCottage/availability", postData).toPromise().then(data => {
      if(!data){alert("Cottage alredy busy")}
      else{this.router.navigate(['/mycottages']);}
    });
  }
}
