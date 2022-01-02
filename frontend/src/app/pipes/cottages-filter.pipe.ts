import { Pipe, PipeTransform } from '@angular/core';
import { HomeForGuestsComponent } from '../home-for-guests/home-for-guests.component';

@Pipe({
  name: 'cottagesFilter',
  pure: false
})
export class CottagesFilterPipe implements PipeTransform {

  constructor(private homeForGuestsComponent: HomeForGuestsComponent){

  }

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.homeForGuestsComponent.getName();
    var address = this.homeForGuestsComponent.getAddress();
    var description = this.homeForGuestsComponent.getDescription();
    const resultArray = [];
    if(value.length === 0 || name === '' || propName === ''){
      return value;
    }
    for(const item of value){
      if(item['name'].includes(name) && item['address'].includes(address) && item['description'].includes(description)){
        resultArray.push(item);
      }
    }
    
    return resultArray;
  }

}
