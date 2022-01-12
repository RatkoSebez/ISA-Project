import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-adventures-page',
  templateUrl: './adventures-page.component.html',
  styleUrls: ['./adventures-page.component.css']
})
export class AdventuresPageComponent implements OnInit {

  public name = '';
  public description = '';
  public address = '';
  public capacity!: number;
  public people!: number;
  public days!: number;
  public date1!: Date;
  public date2!: Date;
  public boatId = 8;

  public constructor(private http: HttpClient, private router: Router) {}

  public getName(){
    return this.name;
  }
  public getDescription(){
    return this.description;
  }
  public getAddress(){
    return this.address;
  }
  // public getCapacityBoat(){
  //   return this.capacity;
  // }
  // public getDaysBoat(){
  //   return this.days;
  // }
  // public getPeopleBoat(){
  //   return this.people;
  // }
  public getDate1Adventure() : Date{
    return this.date1;
  }
  public getDate2Adventure() : Date{
    return this.date2;
  }
  public getSortSelectValue(){
    var e = (document.getElementById("sortAttribute")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var value = (<HTMLSelectElement><unknown>opt).value;
    //console.log(value);
    return value;
  }
  public getSortType(){
    var e = (document.getElementById("sortType")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var value = (<HTMLSelectElement><unknown>opt).value;
    //console.log(value);
    return value;
  }

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
