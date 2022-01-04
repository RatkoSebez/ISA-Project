import { Component, OnInit } from '@angular/core';
import { CardComponent } from '../weekend-cottage-card/card.component';

@Component({
  selector: 'app-weekend-cottages-page',
  templateUrl: './weekend-cottages-page.component.html',
  styleUrls: ['./weekend-cottages-page.component.css']
})
export class WeekendCottagesPageComponent implements OnInit {
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
