import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-weekend-cottages-page',
  templateUrl: './weekend-cottages-page.component.html',
  styleUrls: ['./weekend-cottages-page.component.css']
})
export class WeekendCottagesPageComponent implements OnInit {
  public nameCottage = '';
  public addressCottage = '';
  public descriptionCottage = '';

  public constructor() {}

  public getNameCottage(){
    return this.nameCottage;
  }
  public getAddressCottage(){
    return this.addressCottage;
  }
  public getDescriptionCottage(){
    return this.descriptionCottage;
  }

  ngOnInit(): void {
  }
}
