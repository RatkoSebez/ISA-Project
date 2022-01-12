import { Pipe, PipeTransform } from '@angular/core';
import { AdventureCardComponent } from '../adventure-card/adventure-card.component';
import { AdventuresPageComponent } from '../adventures-page/adventures-page.component';
import { Adventure } from '../model/Adventure';

@Pipe({
  name: 'andventuresFilter',
  pure: false
})
export class AndventuresFilterPipe implements PipeTransform {

  constructor(private adventurePageComponent: AdventuresPageComponent, private adventureCardComponent: AdventureCardComponent){}

  transform(value: any[], filter: string, propName: string): any[] {
    console.log('ovde')
    var name = this.adventurePageComponent.getName();
    var address = this.adventurePageComponent.getAddress();
    var description = this.adventurePageComponent.getDescription();
    var date1 = this.adventurePageComponent.getDate1Adventure();
    var date2 = this.adventurePageComponent.getDate2Adventure();
    var sortBy = this.adventurePageComponent.getSortSelectValue();
    var sortType = this.adventurePageComponent.getSortType();
    var adventures: Array<Adventure> = this.adventureCardComponent.getAdventures();
    const resultArray = [];

    var br = 0;
    for(let adventure of adventures){
      var ok = true;
      br += 1;
      for(let reservation of adventure.reservations){
        if(new Date(date1).getTime() > new Date(reservation.endDate).getTime() || new Date(date2).getTime() < new Date(reservation.startDate).getTime() || !date1 || !date2){}
        else ok = false;
      }
      if(ok) resultArray.push(adventure);
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
