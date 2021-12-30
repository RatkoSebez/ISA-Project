import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user: any;

  constructor(private http: HttpClient) { }

  ngOnInit() {  
    this.doGet();
  }

  doGet(){
    // console.log("yo");
    // this.http.get('api/client').toPromise().then(data => {
    //   console.log(data);
    // });  
    // this.http.get<any>('api/client').subscribe(data => {
    //     this.getData = JSON.stringify(data.total);
    // })    
    this.http.get('api/client').subscribe(val => this.user = val);
    //console.log(this.getData);
  }

  // doPost(){
  //   this.http.post<any>('http://localhost:8080/login', { 'username': 'user', 'password': '123' }).subscribe(data => {
        
  //   })
  // }

  // logout(){
  //   this.http.get('http://localhost:8080/login?logout').subscribe();
  //   console.log('ovde');
  // }

}
