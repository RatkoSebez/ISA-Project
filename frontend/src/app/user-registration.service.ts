import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from './client';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  title: any;

  constructor(private http:HttpClient) { }

  public doRegistration(client: Client){
    return this.http.post("/api/client", "ratko", {responseType:'text'});
    // return this.http.post("/api/client", client, {responseType:'text' as 'json'});
  }

  // public doGet(){
  //   this.title = this.http.get('/api/client').map(res => res.json());
  // }
}
