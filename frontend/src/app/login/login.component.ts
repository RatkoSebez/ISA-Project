import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!: string;
  errorMessage = 'invalid credentials';
  successMessage!: string;
  invalidLogin = false;
  loginSuccess = false;
  postData = {
    username: "user",
    password: "123"
  }

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  login(){
    // console.log('clicked');
    // this.authService.login(this.username, this.password).subscribe((result) => {
    //   this.invalidLogin = false;
    //   this.loginSuccess = true;
    //   this.successMessage = 'Login success';
    // }, () => {
    //   this.invalidLogin = true;
    //   this.loginSuccess = false;
    // });
    this.http.post("login", this.postData).toPromise().then(data => {
      console.log(data);
    });
  }

}
