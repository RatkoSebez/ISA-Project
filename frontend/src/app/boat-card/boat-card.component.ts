import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { Boat } from '../model/Boat';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-boat-card',
  templateUrl: './boat-card.component.html',
  styleUrls: ['./boat-card.component.css']
})
export class BoatCardComponent implements OnInit {

  boats!: Boat[];
  show = false;
  additionalServices = "";

  constructor(private http: HttpClient, private component: BoatsPageComponent) { }

  ngOnInit(): void {
    this.doGet();
  }

  public getBoats() : Boat[]{
    return this.boats;
  }

  public makeReservation(id: number){
    this.component.makeReservation(id, this.additionalServices);
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

  addAdditionalService(additionalService: string){
    this.additionalServices += additionalService + ",";
  }

}
