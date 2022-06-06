import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Boat } from '../model/Boat';
import { User } from '../model/User';

@Component({
  selector: 'app-owner-boat-card',
  templateUrl: './owner-boat-card.component.html',
  styleUrls: ['./owner-boat-card.component.css']
})
export class OwnerBoatCardComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  public nameBoat = '';
  public descriptionBoat = '';
  public address = '';
  public capacityBoat!: number;
  public people!: number;
  public days!: number;
  public date1!: Date;
  public date2!: Date;
  public boatId = 8;
  public show = false;
  todayDate: Date = new Date();

  boats!: Boat[];
  
  public getBoats() : Boat[]{
    return this.boats;
  }
  public getNameBoat(){
    return this.nameBoat;
  }
  public getDescriptionBoat(){
    return this.descriptionBoat;
  }
  public getAddress(){
    return this.address;
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

  ngOnInit(): void {
    this.doGet();
  }

  doGet(){ 
    let llogged = null
    this.http.get<User>('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
        llogged = val.id;
        console.log(llogged);
        this.doSome(llogged);
      }
    });
  }

  doSome(llogged: any){
    console.log(llogged)
    let id = llogged;
    this.http.get<any>('api/boat/myboats/' + llogged).subscribe(response => {
        this.boats = response;
        console.log(this.boats);
    });
  }

}
