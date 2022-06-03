import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/User';
import { WeekendCottage } from '../model/WeekendCottage';
import { WeekendCottagesPageComponent } from '../weekend-cottages-page/weekend-cottages-page.component';

@Component({
  selector: 'app-owner-cottage-card',
  templateUrl: './owner-cottage-card.component.html',
  styleUrls: ['./owner-cottage-card.component.css']
})
export class OwnerCottageCardComponent implements OnInit {
  
  weekendCottages!: WeekendCottage[];
  show = false;
  additionalServices = "";

  public nameCottage = '';
  public addressCottage = '';
  public descriptionCottage = '';
  public people!: number;
  public date1!: Date;
  public date2!: Date;
  todayDate: Date = new Date();

  constructor(public http: HttpClient, private router: Router) { }

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

  ngOnInit(): void {
    this.doGet();
  }

  public getCottages() : WeekendCottage[]{
    return this.weekendCottages;
  }

  doGet(){ 
    let llogged = null
    this.http.get<User>('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
        llogged = val.id;
        console.log(llogged);
        this.doSome(llogged);
      }
    });
  }

  doSome(llogged: any){
    console.log(llogged)
    let id = llogged;
    this.http.get<any>('api/weekendCottage/mycottages/' + llogged).subscribe(response => {
        this.weekendCottages = response;
        console.log(this.weekendCottages);
    });
  }

  addCottage(){
    this.router.navigate(['/addCottage']);
  }

}

