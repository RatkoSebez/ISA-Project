import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { AvailableReservations } from '../model/AvailableReservations';
import { WeekendCottage } from '../model/WeekendCottage';
import { WeekendCottagesPageComponent } from '../weekend-cottages-page/weekend-cottages-page.component';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  weekendCottages!: WeekendCottage[];
  show = false;
  reserve = false;
  additionalServices = "";
  availableReservation !: AvailableReservations[];

  constructor(private http: HttpClient, public component: WeekendCottagesPageComponent) { }

  ngOnInit(): void {
    this.doGet();
    this.http.get<any>('api/weekendCottage').subscribe(
      response => {
        this.weekendCottages = response;
        //console.log(this.weekendCottages)
      }
    );
    this.http.get<any>('api/weekendCottage/availableReservations').subscribe(
      response => {
        this.availableReservation = response;
        //console.log(this.weekendCottages)
      }
    );
  }

  public makeReservation(id: number, price: number){
    this.component.makeReservation(id, price, this.additionalServices);
  }

  public getCottages() : WeekendCottage[]{
    return this.weekendCottages;
  }

  public getAR() : AvailableReservations[]{
    return this.availableReservation;
  }

  doGet(){ 
    this.http.get('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        //console.log("yo")
        this.show = true;
      }
    });
  }

  addAdditionalService(additionalService: string){
    this.additionalServices += additionalService + ",";
  }
}
