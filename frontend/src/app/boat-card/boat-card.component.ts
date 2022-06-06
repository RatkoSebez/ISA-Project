import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { AvailableReservations } from '../model/AvailableReservations';
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
  reserve = false;
  additionalServices = "";
  availableReservation !: AvailableReservations[];

  constructor(private http: HttpClient, private component: BoatsPageComponent) { }

  ngOnInit(): void {
    this.doGet();
    this.http.get<any>('api/boat').subscribe(
      response => {
        this.boats = response;
      }
    );
    this.http.get<any>('api/boat/availableReservations').subscribe(
      response => {
        this.availableReservation = response;
      }
    );
    
  }

  public getBoats() : Boat[]{
    return this.boats;
  }

  public getAR() : AvailableReservations[]{
    return this.availableReservation;
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
      }
    });
  }

  addAdditionalService(additionalService: string){
    this.additionalServices += additionalService + ",";
  }

}
