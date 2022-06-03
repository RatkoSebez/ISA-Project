import { formatDate } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
import { AvailableReservations } from '../model/AvailableReservations';
import { WeekendCottage } from '../model/WeekendCottage';
import { CardComponent } from '../weekend-cottage-card/card.component';
import { WeekendCottagesPageComponent } from '../weekend-cottages-page/weekend-cottages-page.component';
import { DatePipe } from '@angular/common'
import * as moment from 'moment';

@Pipe({
  name: 'cottagesFilter',
  pure: false
})
export class CottagesFilterPipe implements PipeTransform {

  constructor(private weekendCottagesPageComponent: WeekendCottagesPageComponent, private weekendCottageCardComponent: CardComponent, public detepipe: DatePipe){}

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.weekendCottagesPageComponent.getNameCottage();
    var address = this.weekendCottagesPageComponent.getAddressCottage();
    var description = this.weekendCottagesPageComponent.getDescriptionCottage();
    var sortBy = this.weekendCottagesPageComponent.getSortSelectValue();
    var sortType = this.weekendCottagesPageComponent.getSortType();
    var cottages: Array<WeekendCottage> = this.weekendCottageCardComponent.getCottages();
    var availableRes: Array<AvailableReservations> = this.weekendCottageCardComponent.getAR();
    var date1 = this.weekendCottagesPageComponent.getDate1Cottage();
    var date2 = this.weekendCottagesPageComponent.getDate2Cottage();
    var start = moment(date1).format("DD/MM/YYYY");
    var end = moment(date2).format("DD/MM/YYYY");
    const resultArray = [];

    if(date1 && date2){this.weekendCottageCardComponent.reserve = true}
  
    var br = 0;
    var bok = false;
    for(let cottage of cottages){
      var ok = true;
      br += 1;
      for(let ar of availableRes){
        if(ar.entityId === cottage.id){
          console.log("Slobodno od ", this.convertToLocalDate(ar.startDate))
          console.log(this.convertToLocalDate(start))
          console.log("Slobodno do ",this.convertToLocalDate(ar.endDate))
          console.log(this.convertToLocalDate(end))
          if(new Date(this.convertToLocalDate(start)).getTime() >= new Date(this.convertToLocalDate(ar.startDate)).getTime() && new Date(this.convertToLocalDate(end)).getTime() <= new Date(this.convertToLocalDate(ar.endDate)).getTime() && new Date(this.convertToLocalDate(start)).getTime() <= new Date(this.convertToLocalDate(ar.endDate)).getTime() && new Date(this.convertToLocalDate(end)).getTime() >= new Date(this.convertToLocalDate(ar.startDate)).getTime() || !date1 || !date2){bok=true;}
          else ok = false;
        }else ok = false;
      }
      
      if(ok || bok) resultArray.push(cottage);
      bok = false;
    }
    
    const resultArray2 = [];
    
    for(const item of resultArray){
      if(item['name'].includes(name) && item['address'].includes(address) && item['description'].includes(description)){
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

