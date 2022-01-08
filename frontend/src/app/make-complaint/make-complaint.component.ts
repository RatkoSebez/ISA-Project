import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Adventure } from '../model/Adventure';
import { Boat } from '../model/Boat';
import { User } from '../model/User';
import { WeekendCottage } from '../model/WeekendCottage';

@Component({
  selector: 'app-make-complaint',
  templateUrl: './make-complaint.component.html',
  styleUrls: ['./make-complaint.component.css']
})
export class MakeComplaintComponent implements OnInit {

  public user!: User;
  public boatsIds: number[] = [];
  public boatOwnersIds: number[] = [];
  public weekendCottagesIds: number[] = [];
  public cottageOwnersIds: number[] = [];
  public adventureIds: number[] = [];
  public boats: Array<Boat> = [];
  public boatOwners: Array<User> = [];
  public cottages: Array<WeekendCottage> = [];
  public adventures: Array<Adventure> = [];
  public compliant: String = "";

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    
    this.http.get<any>('api/user').subscribe(val => {
      this.user = val;
      console.log(this.user.potentialComplaints);
      for(let potentialComplaint of this.user.potentialComplaints){
        if(potentialComplaint.entity == "BOAT"){
          this.boatsIds.push(potentialComplaint.entityId);
        }
        if(potentialComplaint.entity == "BOAT_OWNER"){
          this.boatOwnersIds.push(potentialComplaint.entityId);
        }
        if(potentialComplaint.entity == "WEEKEND_COTTAGE"){
          this.weekendCottagesIds.push(potentialComplaint.entityId);
        }
        if(potentialComplaint.entity == "COTTAGE_OWNER"){
          this.cottageOwnersIds.push(potentialComplaint.entityId);
        }
        if(potentialComplaint.entity == "ADVENTURE"){
          this.adventureIds.push(potentialComplaint.entityId);
        }
      }
      //console.log("adventure ids" + this.adventureIds)
      //boats
      this.http.get<any>("api/boat").subscribe(data => {
        for(let boat of data){
          for(let boatId of this.boatsIds){
            if(boat.id == boatId){
              this.boats.push(boat);
              break;
            }
          }
        }
        console.log(this.boats);
      });
      //cottages
      this.http.get<any>("api/weekendCottage").subscribe(data => {
        for(let cottage of data){
          for(let cottageId of this.weekendCottagesIds){
            if(cottage.id == cottageId){
              this.cottages.push(cottage);
              break;
            }
          }
        }
        console.log(this.cottages);
      });
      //adventures
      this.http.get<any>("api/adventure").subscribe(data => {
        for(let adventure of data){
          for(let adventureId of this.adventureIds){
            if(adventure.id == adventureId){
              this.adventures.push(adventure);
              break;
            }
          }
        }
        console.log(this.adventures);
      });
      //boat owners
      // this.http.get<any>("api/user/getAllUsers").subscribe(data => {
      //   for(let boatOwner of data){
      //     for(let id of this.boatOwnersIds){
      //       if(boatOwner.id == id){
      //         this.boatOwners.push(boatOwner);
      //         break;
      //       }
      //     }
      //   }
      //   console.log(this.boatOwners);
      // });
      // this.http.post<any>("api/boat/boatsForComplaint", this.boatsIds).then(data => {
      //   this.boats = data;
      //   console.log(this.boats);
      // });
    });
  }

  makeBoatCompliant(boatId: number){
    var postData = {
      compliant: this.compliant,
      idOfEntity: boatId,
      entity: "BOAT"
    }
    this.http.post("api/boat/compliant", postData).toPromise().then(data => {
      console.log(data);
    });
  }

  makeCottageCompliant(cottageId: number){
    var postData = {
      compliant: this.compliant,
      idOfEntity: cottageId,
      entity: "WEEKEND_COTTAGE"
    }
    this.http.post("api/weekendCottage/compliant", postData).toPromise().then(data => {
      console.log(data);
    });
  }

  makeAdventureCompliant(adventureId: number){
    var postData = {
      compliant: this.compliant,
      idOfEntity: adventureId,
      entity: "ADVENTURE"
    }
    this.http.post("api/adventure/compliant", postData).toPromise().then(data => {
      console.log(data);
    });
  }

}
