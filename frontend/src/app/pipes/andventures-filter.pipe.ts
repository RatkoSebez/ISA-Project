import { Pipe, PipeTransform } from '@angular/core';
import { AdventuresPageComponent } from '../adventures-page/adventures-page.component';

@Pipe({
  name: 'andventuresFilter',
  pure: false
})
export class AndventuresFilterPipe implements PipeTransform {

  constructor(private adventurePageComponent: AdventuresPageComponent){}

  transform(value: any[], filter: string, propName: string): any[] {
    console.log('ovde')
    var name = this.adventurePageComponent.getName();
    var address = this.adventurePageComponent.getAddress();
    var description = this.adventurePageComponent.getDescription();
    var sortBy = this.adventurePageComponent.getSortSelectValue();
    var sortType = this.adventurePageComponent.getSortType();
    const resultArray = [];
    for(const item of value){
      if(item['name'].includes(name) && item['address'].includes(address) && item['description'].includes(description)){
        resultArray.push(item);
      }
    }

    console.log("sortiram")
    if(sortType == "Ascending"){
      if(sortBy == "Sort by"){}
      else if(sortBy == "Name") resultArray.sort((a,b) => a.name.localeCompare(b.name));
      else if(sortBy == "Location") resultArray.sort((a,b) => a.address.localeCompare(b.address));
      else if(sortBy == "Rating") resultArray.sort((a,b) => a.rating - b.rating);
    }
    else if(sortType == "Descending"){
      if(sortBy == "Sort by"){}
      else if(sortBy == "Name") resultArray.sort((b,a) => a.name.localeCompare(b.name));
      else if(sortBy == "Location") resultArray.sort((b,a) => a.address.localeCompare(b.address));
      else if(sortBy == "Rating") resultArray.sort((b,a) => a.rating - b.rating);
    }
    
    return resultArray;
  }

}
