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
    const resultArray = [];
    // if(value.length === 0 || name === '' || propName === ''){
    //   return value;
    // }
    for(const item of value){
      if(item['name'].includes(name) && item['address'].includes(address) && item['description'].includes(description)){
        resultArray.push(item);
      }
    }
    
    return resultArray;
  }

}
