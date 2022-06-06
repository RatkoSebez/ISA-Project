import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
import { BoatCardComponent } from '../boat-card/boat-card.component';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { HomeForGuestsComponent } from '../home-for-guests/home-for-guests.component';
import { AvailableReservations } from '../model/AvailableReservations';
import { Boat } from '../model/Boat';
import * as moment from 'moment';

@Pipe({
  name: 'boatsFilter',
  pure: false
})
export class MyBoatsFilterPipe implements PipeTransform {

  constructor(private boatsPageComponent: BoatsPageComponent, private boatCardComponent: BoatCardComponent, public detepipe: DatePipe) {
  }

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.boatsPageComponent.getNameBoat();
    var description = this.boatsPageComponent.getDescriptionBoat();
    var address = this.boatsPageComponent.getAddress();
    var date1 = this.boatsPageComponent.getDate1Boat();
    var date2 = this.boatsPageComponent.getDate2Boat();
    var people = this.boatsPageComponent.getPeopleBoat();
    var days = this.boatsPageComponent.getDaysBoat();
    var boats: Array<Boat> = this.boatCardComponent.getBoats();
    const resultArray = [];
    var sortBy = this.boatsPageComponent.getSortSelectValue();
    var sortType = this.boatsPageComponent.getSortType();
    var boats: Array<Boat> = this.boatCardComponent.getBoats();
    var availableRes: Array<AvailableReservations> = this.boatCardComponent.getAR();
    var date1 = this.boatsPageComponent.getDate1Boat();
    var date2 = this.boatsPageComponent.getDate2Boat();
    var start = moment(date1).format("DD/MM/YYYY");
    var end = moment(date2).format("DD/MM/YYYY");

    if(date1 && date2){this.boatCardComponent.reserve = true}

    var br = 0;
    var bok = false;
    for (let boat of boats) {
      var ok = true;
      br += 1;
      for(let ar of availableRes){
        if(ar.entityId === boat.id){
          console.log("Slobodno od ", this.convertToLocalDate(ar.startDate))
          console.log(this.convertToLocalDate(start))
          console.log("Slobodno do ",this.convertToLocalDate(ar.endDate))
          console.log(this.convertToLocalDate(end))
          if(new Date(this.convertToLocalDate(start)).getTime() >= new Date(this.convertToLocalDate(ar.startDate)).getTime() && new Date(this.convertToLocalDate(end)).getTime() <= new Date(this.convertToLocalDate(ar.endDate)).getTime() && new Date(this.convertToLocalDate(start)).getTime() <= new Date(this.convertToLocalDate(ar.endDate)).getTime() && new Date(this.convertToLocalDate(end)).getTime() >= new Date(this.convertToLocalDate(ar.startDate)).getTime() || !date1 || !date2){bok=true;}
          else ok = false;
        }else ok = false;
      }
      
      if(ok || bok) resultArray.push(boat);
      bok = false;
    }

    const resultArray2 = [];

    for (const item of resultArray) {
      if (item['name'].includes(name) && item['description'].includes(description) && item['address'].includes(address)) {
        resultArray2.push(item);
      }
    }

    console.log("sortiram")
    if (sortType == "Ascending") {
      if (sortBy == "Sort by") { }
      else if (sortBy == "Name") resultArray2.sort((a, b) => a.name.localeCompare(b.name));
      else if (sortBy == "Location") resultArray2.sort((a, b) => a.address.localeCompare(b.address));
      else if (sortBy == "Rating") resultArray2.sort((a, b) => a.rating - b.rating);
    }
    else if (sortType == "Descending") {
      if (sortBy == "Sort by") { }
      else if (sortBy == "Name") resultArray2.sort((b, a) => a.name.localeCompare(b.name));
      else if (sortBy == "Location") resultArray2.sort((b, a) => a.address.localeCompare(b.address));
      else if (sortBy == "Rating") resultArray2.sort((b, a) => a.rating - b.rating);
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
    return '' + y + '-' + (m <= 9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
  }

  convertToLocalDate(responseDate: any) {
    try {
      if (responseDate != null) {
        if (typeof (responseDate) === 'string') {
          if (String(responseDate.indexOf(' ') >= 0)) {
            responseDate = responseDate.split(' ')[0];
          }
          if (String(responseDate.indexOf('+') >= 0)) {
            responseDate = responseDate.split('+')[0];
          }
        }
        const [d, m, y] = responseDate.split('/')
        const nwDate = new Date(y, m, d)
        responseDate = new Date(responseDate);
        const newDate = new Date(responseDate.getFullYear(), responseDate.getMonth(), responseDate.getDate(), 0, 0, 0);
        const userTimezoneOffset = newDate.getTimezoneOffset() * 60000;

        const finalDate: Date = new Date(newDate.getTime() - userTimezoneOffset);
        return nwDate;
      } else {
        return null;
      }
    } catch (error) {
      return responseDate;
    }
  }

}