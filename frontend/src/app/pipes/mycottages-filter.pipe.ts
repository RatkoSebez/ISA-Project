import { Pipe, PipeTransform } from '@angular/core';
import { WeekendCottage } from '../model/WeekendCottage';
import { OwnerCottageCardComponent } from '../owner-cottage-card/owner-cottage-card.component';
import { CardComponent } from '../weekend-cottage-card/card.component';
import { WeekendCottagesPageComponent } from '../weekend-cottages-page/weekend-cottages-page.component';

@Pipe({
  name: 'myCottagesFilter',
  pure: false
})
export class MyCottagesFilterPipe implements PipeTransform {

  constructor(private weekendCottagesPageComponent: OwnerCottageCardComponent){}

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.weekendCottagesPageComponent.getNameCottage();
    var address = this.weekendCottagesPageComponent.getAddressCottage();
    var description = this.weekendCottagesPageComponent.getDescriptionCottage();
    var sortBy = this.weekendCottagesPageComponent.getSortSelectValue();
    var sortType = this.weekendCottagesPageComponent.getSortType();
    var cottages: Array<WeekendCottage> = this.weekendCottagesPageComponent.getCottages();
    var date1 = this.weekendCottagesPageComponent.getDate1Cottage();
    var date2 = this.weekendCottagesPageComponent.getDate2Cottage();
    const resultArray = [];

    var br = 0;
    for(let cottage of cottages){
      var ok = true;
      br += 1;
      for(let reservation of cottage.reservations){
        if(new Date(date1).getTime() < new Date(reservation.endDate).getTime() || new Date(date2).getTime() > new Date(reservation.startDate).getTime() || !date1 || !date2){}
        else ok = false;
      }
      if(ok) resultArray.push(cottage);
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

}
