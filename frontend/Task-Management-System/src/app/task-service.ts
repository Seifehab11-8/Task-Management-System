import { Injectable, signal } from '@angular/core';
import { Task } from './model/task.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  constructor(private http: HttpClient) {}
  private url = 'http://localhost:8080';
  
  getTasks() {
    console.log('Fetching tasks...');
    return this.http.get<Task[]>(`${this.url}/tasks`, { headers: this.getHeaders() , responseType: 'json' });
  }

  getTasksByStatus(status: string) {
    return this.http.get<Task[]>(`${this.url}/tasks?status=${status}`, { headers: this.getHeaders() , responseType: 'json' });
  }

  getTasksByTitle(title: string) {
    console.log('Searching tasks by title:', title);
    return this.http.get<Task[]>(`${this.url}/tasks?title=${title}`, { headers: this.getHeaders() , responseType: 'json' });
  }

  getTaskById(id: number) {
    return this.http.get<Task>(`${this.url}/tasks/${id}`, { headers: this.getHeaders() , responseType: 'json' });
  }

  addTask(task: Task) {
    return this.http.post<Task>(`${this.url}/tasks`, task, { headers: this.getHeaders() , responseType: 'json' });
  }

  updateTask(task: Task) {
    return this.http.put<Task>(`${this.url}/tasks/${task.id}`, task, { headers: this.getHeaders() , responseType: 'json' });
  }

  deleteTask(taskId: number) {
    return this.http.delete(`${this.url}/tasks/${taskId}`, { headers: this.getHeaders() });
  }

  getHeaders() {
    return new HttpHeaders({
      Authorization: 'Basic ' + btoa(sessionStorage.getItem('username') + ':' + sessionStorage.getItem('password'))
  });;
  }
}
