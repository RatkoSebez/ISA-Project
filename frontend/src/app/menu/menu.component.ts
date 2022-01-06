import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user: any;
  show = true;
  login = true;
  register = true;
  about = true;
  pricing = true;
  logout = false;
  client = false;

  constructor(private http: HttpClient, private renderer: Renderer2, private elem: ElementRef) { }

  ngOnInit() { 
    this.doGet();
  }

  doGet(){ 
    this.http.get('api/user').subscribe(val => {
      this.user = val;
      console.log(val);
      if(val) {
        this.show = false;
      }
      if(this.user.role == "ROLE_CLIENT") this.client = true;
    });
  }

  changeShow(){
    this.show = true;
  }
}
