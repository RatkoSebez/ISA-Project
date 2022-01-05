import { Pipe, PipeTransform } from '@angular/core';
import { BoatCardComponent } from '../boat-card/boat-card.component';
import { BoatsPageComponent } from '../boats-page/boats-page.component';
import { HomeForGuestsComponent } from '../home-for-guests/home-for-guests.component';
import { Boat } from '../model/Boat';

@Pipe({
  name: 'boatsFilter',
  pure: false
})
export class BoatsFilterPipe implements PipeTransform {

  constructor(private boatsPageComponent: BoatsPageComponent, private boatCardComponent: BoatCardComponent){
  }

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.boatsPageComponent.getNameBoat();
    var description = this.boatsPageComponent.getDescriptionBoat();
    var date1 = this.boatsPageComponent.getDate1Boat();
    var date2 = this.boatsPageComponent.getDate2Boat();
    var people = this.boatsPageComponent.getPeopleBoat();
    var days = this.boatsPageComponent.getDaysBoat();
    var boats: Array<Boat> = this.boatCardComponent.getBoats();
    const resultArray = [];

    //var dateFormat = require('dateformat');
    
    var br = 0;
    for(let boat of boats){
      var ok = true;
      br += 1;
      //console.log("-----------boat" + br)
      for(let reservation of boat.reservations){
        //console.log(reservation.startDate + " - " + reservation.endDate);
        //console.log(date1 + " - " + date2);
        //date1 = dateFormat(date1, "YYYY-MM-DD");
        //date2 = dateFormat(date2, "YYYY-MM-DD");
        //date1 = moment("21/10/14", "DD/MM/YY").format("MM/DD/YY")

        //reservation.startDate = new Date(this.dateToYMD(new Date(reservation.startDate.getFullYear(), reservation.startDate.getMonth(), reservation.startDate.getDate())));

        //console.log("---")
        //console.log(date1 + " > " + reservation.endDate + " - " + (date1 > reservation.endDate))
        //console.log(date2 + " < " + reservation.startDate + " - " + (date2 < reservation.startDate))
        //console.log("s" + date1 + " - " + reservation.endDate)
        if(date1 > reservation.endDate || date2 < reservation.startDate || !date1 || !date2){}
        else ok = false;
      }
      if(ok) resultArray.push(boat);
    }

    const resultArray2 = [];

    for(const item of resultArray){
      if(item['name'].includes(name) && item['description'].includes(description)){
        resultArray2.push(item);
      }
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

