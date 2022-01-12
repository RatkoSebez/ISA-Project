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
    const resultArray = [];
    for(const item of value){
      if(item['name'].includes(name) && item['address'].includes(address) && item['description'].includes(description)){
        resultArray.push(item);
      }
    }
    
    return resultArray;
  }

}
