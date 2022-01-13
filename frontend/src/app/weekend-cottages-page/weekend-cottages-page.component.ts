import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-weekend-cottages-page',
  templateUrl: './weekend-cottages-page.component.html',
  styleUrls: ['./weekend-cottages-page.component.css']
})
export class WeekendCottagesPageComponent implements OnInit {
  public nameCottage = '';
  public addressCottage = '';
  public descriptionCottage = '';
  public people!: number;
  public date1!: Date;
  public date2!: Date;
  public cottageId = 6;
  public show = false;

  public constructor(private http: HttpClient, private router: Router) {}

  public getNameCottage(){
    return this.nameCottage;
  }
  public getAddressCottage(){
    return this.addressCottage;
  }
  public getDescriptionCottage(){
    return this.descriptionCottage;
  }
  public getPeopleCottage(){
    return this.people;
  }
  public getDate1Cottage() : Date{
    return this.date1;
  }
  public getDate2Cottage() : Date{
    return this.date2;
  }
  public getSortSelectValue(){
    var e = (document.getElementById("sortAttribute")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var value = (<HTMLSelectElement><unknown>opt).value;
    //console.log(value);
    return value;
  }
  public getSortType(){
    var e = (document.getElementById("sortType")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var value = (<HTMLSelectElement><unknown>opt).value;
    //console.log(value);
    return value;
  }

  public makeReservation(id: number, additionalServices: string){
    var postData = {
      guests: this.people,
      startDate: this.date1,
      endDate: this.date2,
      boatId: id,
      additionalServices: additionalServices
    }
    this.http.post("api/weekendCottage/reservation", postData).toPromise().then(data => {
      console.log(data);
    });
    this.router.navigate(['/cottageReservations']);
    //console.log("rezervisem")
  }

  goToDiscountPage(){
    this.router.navigate(['/fastReservationCottage']);
  }

  ngOnInit(): void {
    this.http.get('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
      }
    });
  }
}
