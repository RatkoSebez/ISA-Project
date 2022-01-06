import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { Boat } from '../model/Boat';

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

  constructor(private http: HttpClient, private component: BoatsPageComponent) { }

  ngOnInit(): void {
    this.doGet();
    this.http.get<any>('api/boat').subscribe(
      response => {
        this.boats = response;
        //console.log(this.boats);
      }
    );
  }

  public getBoats() : Boat[]{
    return this.boats;
  }

  public makeReservation(id: number){
    this.component.makeReservation(id);
  }

  doGet(){ 
    this.http.get('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
      }
    });
  }

}
