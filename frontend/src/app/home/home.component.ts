import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user!: User;
  guest = true;
  client = false;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.doGet();
  }

  doGet(){ 
    this.http.get<any>('api/user').subscribe(val => {
      this.user = val;
      console.log(this.user.role);
      if(this.user.role == 'ROLE_CLIENT') {
        this.client = true;
        this.guest = false;
      }
    });
  }

}
