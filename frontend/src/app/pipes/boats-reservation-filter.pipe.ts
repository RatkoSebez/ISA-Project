import { Pipe, PipeTransform } from '@angular/core';
import { BoatReservationsComponent } from '../boat-reservations/boat-reservations.component';

@Pipe({
  name: 'boatsReservationFilter',
  pure: false
})
export class BoatsReservationFilterPipe implements PipeTransform {

  constructor(private boatReservationsComponent: BoatReservationsComponent){}

  transform(value: any[], filter: string, propName: string): any[] {
    var sortBy = this.boatReservationsComponent.getSortSelectValue();
    var sortType = this.boatReservationsComponent.getSortType();
    
    const resultArray = [];
    for(const item of value){
        resultArray.push(item);
    }

    console.log("sortiram")
    if(sortType == "Ascending"){
      if(sortBy == "Sort by"){}
      else if(sortBy == "Date") resultArray.sort(function(a,b){return new Date(a.startDate).getTime() - new Date(b.startDate).getTime()});
      else if(sortBy == "Duration") resultArray.sort((a,b) => (Math.abs(new Date(a.startDate).getTime() - new Date(a.endDate).getTime())) - (Math.abs(new Date(b.startDate).getTime() - new Date(b.endDate).getTime())));
      else if(sortBy == "Price") resultArray.sort((a,b) => a.price - b.price);
    }
    else if(sortType == "Descending"){
      if(sortBy == "Sort by"){}
      else if(sortBy == "Date") resultArray.sort(function(b,a){return new Date(a.startDate).getTime() - new Date(b.startDate).getTime()});
      else if(sortBy == "Duration") resultArray.sort((b,a) => (Math.abs(new Date(a.startDate).getTime() - new Date(a.endDate).getTime())) - (Math.abs(new Date(b.startDate).getTime() - new Date(b.endDate).getTime())));
      else if(sortBy == "Price") resultArray.sort((b,a) => a.price - b.price);
    }
    
    return resultArray;
  }

}
