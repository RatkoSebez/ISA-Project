import { Pipe, PipeTransform } from '@angular/core';
import { BoatCardComponent } from '../boat-card/boat-card.component';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { HomeForGuestsComponent } from '../home-for-guests/home-for-guests.component';
import { Boat } from '../model/Boat';
import { OwnerBoatCardComponent } from '../owner-boat-card/owner-boat-card.component';
import { OwnerCottageCardComponent } from '../owner-cottage-card/owner-cottage-card.component';

@Pipe({
  name: 'myboatsFilter',
  pure: false
})
export class BoatsFilterPipe implements PipeTransform {

  constructor(private boatsPageComponent: OwnerBoatCardComponent){
  }

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.boatsPageComponent.getNameBoat();
    var description = this.boatsPageComponent.getDescriptionBoat();
    var address = this.boatsPageComponent.getAddress();
    var date1 = this.boatsPageComponent.getDate1Boat();
    var date2 = this.boatsPageComponent.getDate2Boat();
    var people = this.boatsPageComponent.getPeopleBoat();
    var days = this.boatsPageComponent.getDaysBoat();
    var boats: Array<Boat> = this.boatsPageComponent.getBoats();
    const resultArray = [];
    var sortBy = this.boatsPageComponent.getSortSelectValue();
    var sortType = this.boatsPageComponent.getSortType();
    
    var br = 0;
    for(let boat of boats){
      var ok = true;
      br += 1;
      for(let reservation of boat.reservations){
        if(new Date(date1).getTime() > new Date(reservation.endDate).getTime() || new Date(date2).getTime() < new Date(reservation.startDate).getTime() || !date1 || !date2){}
        else ok = false;
      }
      if(ok) resultArray.push(boat);
    }

    const resultArray2 = [];

    for(const item of resultArray){
      if(item['name'].includes(name) && item['description'].includes(description) && item['address'].includes(address)){
        resultArray2.push(item);
      }
    }

    console.log("sortiram")
    if(sortType == "Ascending"){
      if(sortBy == "Sort by"){}
      else if(sortBy == "Name") resultArray2.sort((a,b) => a.name.localeCompare(b.name));
      else if(sortBy == "Location") resultArray2.sort((a,b) => a.address.localeCompare(b.address));
      else if(sortBy == "Rating") resultArray2.sort((a,b) => a.rating - b.rating);
    }
    else if(sortType == "Descending"){
      if(sortBy == "Sort by"){}
      else if(sortBy == "Name") resultArray2.sort((b,a) => a.name.localeCompare(b.name));
      else if(sortBy == "Location") resultArray2.sort((b,a) => a.address.localeCompare(b.address));
      else if(sortBy == "Rating") resultArray2.sort((b,a) => a.rating - b.rating);
    }

    return resultArray2;
  }

  addDays(date: Date, days: number) {
    var result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  }

  dateToYMD(date: Date) {
    var d = date.getDate();
    var m = date.getMonth() + 1; //Month from 0 to 11
    var y = date.getFullYear();
    return '' + y + '-' + (m<=9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
  }
}
function moment(arg0: string, arg1: string) {
  throw new Error('Function not implemented.');
}

