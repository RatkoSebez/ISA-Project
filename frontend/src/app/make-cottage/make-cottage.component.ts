import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators,FormArray, FormControl } from '@angular/forms';
import { Component, createPlatform, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-make-cottage',
  templateUrl: './make-cottage.component.html',
  styleUrls: ['./make-cottage.component.css']
})
export class MakeCottageComponent implements OnInit {

  public name = null;
  public address = null
  public description = null
  public rules = null
  public pricelist = null
  public additionalservices = null
  selectedFile = null;
  logged !: User;

  constructor(private http: HttpClient, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {

    this.http.get<any>('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.logged = val;
      }
    });

  }

  onSubmit() {
    var Cottage = { 
      name : this.name,
      address : this.address,
      description :  this.description,
      rating:  null,
      rulesOfConduct : this.rules,
      image: this.selectedFile,
      cottageOwnerId : this.logged.id,
      priceList : this.pricelist,
      additionalServices : this.additionalservices
    }

    if(Cottage){
      this.http.post("api/weekendCottage/addCottage", Cottage).toPromise().then(data => {
        console.log(Cottage);
        this.router.navigate(['/mycottages']);
      });
    }
  }

  selectImage(event: any){
    this.selectedFile = event.target.files[0].name;
  }

}
