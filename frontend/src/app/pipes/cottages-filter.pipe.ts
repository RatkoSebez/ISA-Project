import { Pipe, PipeTransform } from '@angular/core';
import { WeekendCottagesPageComponent } from '../weekend-cottages-page/weekend-cottages-page.component';

@Pipe({
  name: 'cottagesFilter',
  pure: false
})
export class CottagesFilterPipe implements PipeTransform {

  constructor(private weekendCottagesPageComponent: WeekendCottagesPageComponent){}

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.weekendCottagesPageComponent.getNameCottage();
    var address = this.weekendCottagesPageComponent.getAddressCottage();
    var description = this.weekendCottagesPageComponent.getDescriptionCottage();
    var sortBy = this.weekendCottagesPageComponent.getSortSelectValue();
    var sortType = this.weekendCottagesPageComponent.getSortType();
    //console.log("u pajpu sam")
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
