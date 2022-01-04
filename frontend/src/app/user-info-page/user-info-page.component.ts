import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { User } from '../model/User';

@Component({
  selector: 'app-user-info-page',
  templateUrl: './user-info-page.component.html',
  styleUrls: ['./user-info-page.component.css']
})
export class UserInfoPageComponent implements OnInit {

  user!: User;

  constructor(private http: HttpClient, private renderer: Renderer2, private elem: ElementRef) { }

  ngOnInit() { 
    this.doGet();
  }

  doGet(){  
    this.http.get<any>('api/user').subscribe(
      response => {
        this.user = response;
        console.log(response);
      }
    );
  }

}
