import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-boats-page',
  templateUrl: './boats-page.component.html',
  styleUrls: ['./boats-page.component.css']
})
export class BoatsPageComponent implements OnInit {
  public nameBoat = '';
  public descriptionBoat = '';
  public capacityBoat!: number;

  public constructor() {}

  public getNameBoat(){
    return this.nameBoat;
  }
  public getDescriptionBoat(){
    return this.descriptionBoat;
  }
  public getCapacityBoat(){
    return this.capacityBoat;
  }

  ngOnInit(): void {
  }
}
