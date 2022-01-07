import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adventures-page',
  templateUrl: './adventures-page.component.html',
  styleUrls: ['./adventures-page.component.css']
})
export class AdventuresPageComponent implements OnInit {

  public nameBoat = '';
  public descriptionBoat = '';
  public capacityBoat!: number;
  public people!: number;
  public days!: number;
  public date1!: Date;
  public date2!: Date;
  public boatId = 8;

  public constructor(private http: HttpClient, private router: Router) {}

  // public getNameBoat(){
  //   return this.nameBoat;
  // }
  // public getDescriptionBoat(){
  //   return this.descriptionBoat;
  // }
  // public getCapacityBoat(){
  //   return this.capacityBoat;
  // }
  // public getDaysBoat(){
  //   return this.days;
  // }
  // public getPeopleBoat(){
  //   return this.people;
  // }
  // public getDate1Boat() : Date{
  //   return this.date1;
  // }
  // public getDate2Boat() : Date{
  //   return this.date2;
  // }

  public makeReservation(id: number){
    var postData = {
      guests: this.people,
      startDate: this.date1,
      endDate: this.date2,
      boatId: id
    }
    this.http.post("api/adventure/reservation", postData).toPromise().then(data => {
      console.log(data);
      this.router.navigate(['/adventureReservations']);
    });
    //console.log("rezervisem")
  }

  ngOnInit(): void {
  }

}
