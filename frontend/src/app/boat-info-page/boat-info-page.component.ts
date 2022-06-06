import { DatePipe, formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Boat } from '../model/Boat';
import { User } from '../model/User';

@Component({
  selector: 'app-boat-info-page',
  templateUrl: './boat-info-page.component.html',
  styleUrls: ['./boat-info-page.component.css']
})
export class BoatInfoPageComponent implements OnInit {

  boat!: Boat;
  show = false;
  edit = true;
  user = false;
  boowner = false;
  id: any
  subscribeButtonText = "subscribe";
  selectedFile = null;
  latitude = 45.2396;
  longitude = 19.8227;

  //Editable
  name: any
  description: any
  address: any
  rating: any
  pricePerDay: any
  additionalServices: any
  type: any
  length: any
  engineNumber: any
  enginePower: any
  maxSpeed: any
  navigation: any
  cancellationConditions: any
  fishingEqu: any

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

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.startTime = "14:00"
    this.endTime = "12:00"
    this.activatedRoute.queryParams.subscribe(params => {
        this.id = params['id'];
        this.http.get<any>('api/boat/' + this.id).subscribe(
          response => {
            this.boat = response;
          }
        );
    });
    this.http.get<User>('api/user').subscribe(val => {
      console.log(val);
      if(val.role === "ROLE_CLIENT") {
        this.user = true;
      }else if(val.role ==="ROLE_BOATOWNER"){
        if(this.boat.boatOwnerId === val.id){this.boowner = true;}
      }
    });
  }

  subscribeToBoat(boatId: number){
    var postData = {
      entity: "BOAT",
      idOfEntity: boatId
    }
    this.http.post("api/user/subscribe", postData).toPromise().then(data => {
    });
  }

  doEdit(){
    this.edit = !this.edit;
  }

  doDeleteBoat(){
    this.http.delete('api/boat/'+ this.boat.id).toPromise().then(data => {
      if(data) this.router.navigate(['/myboats']);
      else{alert("Something went wrong")}
    });
  }

  doEditBoat(){
    if(this.name){this.boat.name = this.name}
    if(this.address){this.boat.address = this.address}
    if(this.description){this.boat.description = this.description}
    if(this.selectedFile){this.boat.image = this.selectedFile}
    if(this.pricePerDay){this.boat.pricePerDay = this.pricePerDay}
    if(this.additionalServices){this.boat.additionalServices = this.additionalServices}
    if(this.type){this.boat.type = this.type}
    if(this.length){this.boat.length = this.length}
    if(this.engineNumber){this.boat.engineNumber = this.engineNumber}
    if(this.enginePower){this.boat.enginePower = this.enginePower}
    if(this.maxSpeed){this.boat.maxSpeed = this.maxSpeed}
    if(this.navigation){this.boat.navigation = this.navigation}
    if(this.cancellationConditions){this.boat.cancellationConditions = this.cancellationConditions}
    if(this.fishingEqu){this.boat.fishingEqu = this.fishingEqu}

    this.http.post('api/boat/editboat', this.boat).toPromise().then(data => {
      if(data) window.location.reload();
      else{alert("Something went wrong")}
    });
  }

  addReservation(){
    var start = formatDate(this.firstDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.lastDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      entity: "BOAT",
      entityId: this.boat.id,
      startDate: start + " " + this.startTime,
      oldPrice: this.currprice,
      endDate: end + " " + this.endTime,
      expirationDate: this.newActionExpirationDate,
      newPrice: this.actionprice,
      fast: true
    }

    this.http.post("api/boat/availability", postData).toPromise().then(data => {
      if(!data){alert("Boat alredy busy")}
      else{window.location.reload();}
    });
  }

  editAvailability(){
    var start = formatDate(this.beginDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.finishDate,'yyyy-MM-dd','en_US');
    
    var postData ={
      entity: "BOAT",
      entityId: this.boat.id,
      startDate: start + " " + this.startTime,
      endDate: end + " " + this.endTime,
      fast: false
    }

    this.http.post("api/boat/availability", postData).toPromise().then(data => {
      if(!data){alert("Boat alredy busy")}
      else{window.location.reload();}
    });
  }

  selectImage(event: any){
    this.selectedFile = event.target.files[0].name;
  }

}
