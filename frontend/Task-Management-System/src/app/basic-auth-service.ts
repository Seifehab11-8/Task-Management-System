import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthService {
  private url = 'http://localhost:8080';
  constructor(private http: HttpClient) {}
  
  getPrivate(username: string, password:string, uri:string) {
    sessionStorage.setItem('username', username);
    sessionStorage.setItem('password', password);
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password)
    });
    return this.http.get(`${this.url}${uri}`, { headers, responseType: 'json' });
  }

  logout() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('password');
  }
}
