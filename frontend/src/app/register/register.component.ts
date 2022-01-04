import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

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
  public explanation = null;
  showExplanation: boolean = false;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  register(){
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
      role: rol,
      explanationOfRegistration: this.explanation
    }
    console.log('registrujem: ' + this.email + this.password + this.firstName + this.lastName);
    this.http.post("api/user", postData).toPromise().then(data => {
      console.log(data);
    });
  }

  onChange(){
    var e = (document.getElementById("roleSelect")) as HTMLSelectElement;
    var sel = e.selectedIndex;
    var opt = e.options[sel];
    var CurValue = (<HTMLSelectElement><unknown>opt).value;
    var rol: number = +CurValue;
    if(rol != 0){
      this.showExplanation = true;
    }
    else{ this.showExplanation = false;}
  }

}
