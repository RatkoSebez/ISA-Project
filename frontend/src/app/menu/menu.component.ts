import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  getData: any;

  constructor(private http: HttpClient) { }

  ngOnInit() {  
  }

  doGet(){
    // console.log("yo");
    // this.http.get('api/client').toPromise().then(data => {
    //   console.log(data);
    // });  
    // this.http.get<any>('api/client').subscribe(data => {
    //     this.getData = JSON.stringify(data.total);
    // })    
    this.http.get('api/client').subscribe(val => console.log(val));
    //console.log(this.getData);
  }

}
