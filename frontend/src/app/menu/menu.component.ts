import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user: any;
  login = true;
  register = true;
  about = true;
  pricing = true;
  logout = false;

  constructor(private http: HttpClient, private renderer: Renderer2, private elem: ElementRef) { }

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
    this.http.get('api/client').subscribe(val => {
      this.user = val;
      if(val) {
        this.logout = true;
        this.login = false;
        this.register = false;
      }
    });
    //console.log(this.getData);
  }

  ngAfterViewInit(){
    // you'll get your through 'elements' below code
    let elements = this.elem.nativeElement.querySelectorAll('.classImLookingFor');
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
