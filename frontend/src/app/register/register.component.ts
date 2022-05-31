import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public email = '';
  public password = '';
  public firstName = '';
  public lastName = '';
  public address = '';
  public country = '';
  public city = '';
  public phoneNumber = '';
  public role = 0;
  public clicked = false;
  public password2 = "";
  public wrongPasswordConfirmationMessage = "*password and confirm password doesn't match";
  public ok = false;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  register(){
    if(this.password != this.password2){
      this.ok = true;
      return;
    }
    this.ok = false;
    this.clicked = true;
    var e = (document.getElementById("roleSelect")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var CurValue = (<HTMLSelectElement><unknown>opt).value;
    var rol: number = +CurValue;
    var postData = {
      email: this.email,
      password: this.password,
      firstName: this.firstName,
      lastName: this.lastName,
      address: this.address,
      country: this.country,
      city: this.city,
      phoneNumber: this.phoneNumber,
      role: rol
    }
    console.log('registrujem: ' + this.email + this.password + this.firstName + this.lastName);
    this.http.post("api/user", postData).toPromise().then(data => {
      console.log(data);
      confirm("We send you verification on mail")
    });
    this.router.navigate(['/home']);
  }

}
