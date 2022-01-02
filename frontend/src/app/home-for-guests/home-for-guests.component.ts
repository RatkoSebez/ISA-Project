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
  public name = '';
  public address = '';
  public description = '';

  public constructor(private cottageComponent: CardComponent) {
    
  }

  public getName(){
    return this.name;
  }
  public getAddress(){
    return this.address;
  }
  public getDescription(){
    return this.description;
  }

  ngOnInit(): void {
  }

}
