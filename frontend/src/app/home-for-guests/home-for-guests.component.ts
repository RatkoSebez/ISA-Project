import { Component, Injectable, OnInit } from '@angular/core';
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

  public constructor(private cottageComponent: CardComponent) {
    
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
  }

}
