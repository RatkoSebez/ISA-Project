import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AdventuresPageComponent } from '../adventures-page/adventures-page.component';
import { Adventure } from '../model/Adventure';

@Component({
  selector: 'app-adventure-card',
  templateUrl: './adventure-card.component.html',
  styleUrls: ['./adventure-card.component.css']
})
export class AdventureCardComponent implements OnInit {

  adventures!: Adventure[];
  show = false;

  constructor(private http: HttpClient, private component: AdventuresPageComponent) { }

  ngOnInit(): void {
    this.doGet();
    this.http.get<any>('api/adventure').subscribe(
      response => {
        this.adventures = response;
        //console.log(this.boats);
      }
    );
  }
  
  public getAdventures() : Adventure[]{
    return this.adventures;
  }

  public makeReservation(id: number){
    this.component.makeReservation(id);
  }

  doGet(){ 
    this.http.get('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
      }
    });
  }

}
