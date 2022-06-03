import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { WeekendCottage } from '../model/WeekendCottage';
import { CardComponent } from '../weekend-cottage-card/card.component';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-home-for-guests',
  templateUrl: './home-for-guests.component.html',
  styleUrls: ['./home-for-guests.component.css']
})
export class HomeForGuestsComponent implements OnInit {
  public nameCottage = '';
  public addressCottage = '';
  public descriptionCottage = '';
  public nameBoat = '';
  public descriptionBoat = '';
  public capacityBoat!: number;
  weekendCottages!: WeekendCottage[];

  public constructor(private http: HttpClient) {
    
  }

  public getNameCottage(){
    return this.nameCottage;
  }
  public getAddressCottage(){
    return this.addressCottage;
  }
  public getDescriptionCottage(){
    return this.descriptionCottage;
  }
  public getNameBoat(){
    return this.nameBoat;
  }
  public getDescriptionBoat(){
    return this.descriptionBoat;
  }
  public getCapacityBoat(){
    //console.log(this.capacityBoat);
    return this.capacityBoat;
  }

  ngOnInit(): void {
    this.http.get<any>('api/weekendCottage').subscribe(
      response => {
        this.weekendCottages = response;
        //console.log(this.weekendCottages)
      }
    );
  }

}
