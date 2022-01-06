import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-boats-page',
  templateUrl: './boats-page.component.html',
  styleUrls: ['./boats-page.component.css']
})
export class BoatsPageComponent implements OnInit {
  public nameBoat = '';
  public descriptionBoat = '';
  public capacityBoat!: number;
  public people!: number;
  public days!: number;
  public date1!: Date;
  public date2!: Date;
  public boatId = 8;

  public constructor(private http: HttpClient, private router: Router) {}

  public getNameBoat(){
    return this.nameBoat;
  }
  public getDescriptionBoat(){
    return this.descriptionBoat;
  }
  public getCapacityBoat(){
    return this.capacityBoat;
  }
  public getDaysBoat(){
    return this.days;
  }
  public getPeopleBoat(){
    return this.people;
  }
  public getDate1Boat() : Date{
    return this.date1;
  }
  public getDate2Boat() : Date{
    return this.date2;
  }

  public makeReservation(id: number){
    var postData = {
      guests: this.people,
      startDate: this.date1,
      endDate: this.date2,
      boatId: id
    }
    this.http.post("api/boat/reservation", postData).toPromise().then(data => {
      console.log(data);
      this.router.navigate(['/boatReservations']);
    });
    //console.log("rezervisem")
  }

  ngOnInit(): void {
  }
}
