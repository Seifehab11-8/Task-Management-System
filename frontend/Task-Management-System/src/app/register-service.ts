import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private url = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  registerUser(userData: any) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(sessionStorage.getItem('username') + ':' + sessionStorage.getItem('password'))
  });
    return this.http.post(`${this.url}/register`, userData, { headers });
  }
}
