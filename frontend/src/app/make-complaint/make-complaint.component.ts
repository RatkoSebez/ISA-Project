import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Boat } from '../model/Boat';
import { User } from '../model/User';

@Component({
  selector: 'app-make-complaint',
  templateUrl: './make-complaint.component.html',
  styleUrls: ['./make-complaint.component.css']
})
export class MakeComplaintComponent implements OnInit {

  public user!: User;
  public boatsIds: number[] = [];
  public boatOwnersIds: number[] = [];
  public boats: Array<Boat> = [];
  public boatOwners: Array<User> = [];
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
      }
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

}
