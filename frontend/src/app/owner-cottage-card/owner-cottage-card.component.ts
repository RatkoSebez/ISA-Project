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

  constructor(public http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.doGet();
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

