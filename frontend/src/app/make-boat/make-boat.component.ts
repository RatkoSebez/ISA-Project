import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-make-boat',
  templateUrl: './make-boat.component.html',
  styleUrls: ['./make-boat.component.css']
})
export class MakeBoatComponent implements OnInit {

  public name = null
  public address = null
  public description = null
  public rules = null
  public pricelist = null
  public additionalservices = null
  type: any;
  length: any;
  engnumber: any;
  engpower: any;
  maxspeed: any;
  capacity: any;
  navigation: any;
  cancel: any;
  fishingEqu: any;

  selectedFile = null;
  longitude: any;
  latitude: any;
  logged !: User;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get<any>('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.logged = val;
      }
    });
  }

  onSubmit() {
    var boat = { 
      name : this.name,
      address : this.address,
      description :  this.description,
      rating:  null,
      rulesOfConduct : this.rules,
      image: this.selectedFile,
      boatOwnerId : this.logged.id,
      pricePerDay : this.pricelist,
      additionalServices : this.additionalservices,
      type: this.type,
      length: this.length,
      engineNumber: this.engnumber,
      enginePower: this.engpower,
      maxSpeed: this.maxspeed,
      capacity: this.capacity,
      navigation: this.navigation,
      cancellationConditions: this.cancel,
      fishingEqu: this.fishingEqu
    }

    if(boat){
      this.http.post("api/boat/addboat", boat).toPromise().then(data => {
        console.log(boat);
        this.router.navigate(['/myboats']);
      });
    }
  }

  selectImage(event: any){
    this.selectedFile = event.target.files[0].name;
  }


}
