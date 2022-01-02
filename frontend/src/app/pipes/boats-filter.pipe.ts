import { Pipe, PipeTransform } from '@angular/core';
import { HomeForGuestsComponent } from '../home-for-guests/home-for-guests.component';

@Pipe({
  name: 'boatsFilter',
  pure: false
})
export class BoatsFilterPipe implements PipeTransform {

  constructor(private homeForGuestsComponent: HomeForGuestsComponent){
  }

  transform(value: any[], filter: string, propName: string): any[] {
    var name = this.homeForGuestsComponent.getNameBoat();
    var description = this.homeForGuestsComponent.getDescriptionBoat();
    //var capacity = this.homeForGuestsComponent.getCapacityBoat();
    //capacity = parseInt(capacity);
    //console.log(typeof(capacity))
    const resultArray = [];
    if(value.length === 0 || name === '' || propName === ''){
      return value;
    }
    for(const item of value){
      if(item['name'].includes(name) && item['description'].includes(description)){
        resultArray.push(item);
      }
    }
    
    return resultArray;
  }

}
