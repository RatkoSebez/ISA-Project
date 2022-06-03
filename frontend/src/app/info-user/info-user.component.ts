import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.css']
})
export class InfoUserComponent implements OnInit {

  user!: User;
  edit = true;
  email = '';
  firstName = '';
  lastName = '';
  city = '';
  country = '';
  address = '';
  phoneNumber!: number;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.http.get<User>('api/user/' + params['email']).subscribe(
        response => {
          this.user = response;
          console.log(response);
          this.email = this.user.email;
          this.firstName = this.user.firstName;
          this.lastName = this.user.lastName;
          this.city = this.user.city;
          this.country = this.user.country;
          this.address = this.user.address;
          this.phoneNumber = this.user.phoneNumber;
        }
      );
    });
  }

}
