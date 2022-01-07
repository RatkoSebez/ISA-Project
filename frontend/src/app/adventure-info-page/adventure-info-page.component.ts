import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Adventure } from '../model/Adventure';

@Component({
  selector: 'app-adventure-info-page',
  templateUrl: './adventure-info-page.component.html',
  styleUrls: ['./adventure-info-page.component.css']
})
export class AdventureInfoPageComponent implements OnInit {

  adventure!: Adventure;
  show = false;
  subscribeButtonText = "subscribe";

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
        let id = params['id'];
        this.http.get<any>('api/adventure/' + id).subscribe(
          response => {
            this.adventure = response;
          }
        );
    });
    this.http.get('api/user').subscribe(val => {
      console.log(val);
      if(val) {
        this.show = true;
      }
    });
  }

  subscribeToAdventure(adventureId: number){
    var postData = {
      entity: "ADVENTURE",
      idOfEntity: adventureId
    }
    this.http.post("api/user/subscribe", postData).toPromise().then(data => {
    });
  }

}
