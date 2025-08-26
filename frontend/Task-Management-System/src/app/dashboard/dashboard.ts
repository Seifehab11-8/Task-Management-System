import { Component, inject, OnInit, signal } from '@angular/core';
import { Task } from '../model/task.model';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
import { TaskService } from '../task-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  tasks = signal<Task[]>([]);
  constructor(private router: Router, private taskService: TaskService) { }
  
  ngOnInit() {
    if(sessionStorage.getItem('username') == null) {
      this.router.navigate(['/login']);
    }
    console.log('Initializing dashboard...');
    this.fillTask();
  }
  
  fillTask() {
    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');
    
    if (username && password) {
      this.taskService.getTasks().subscribe({
        next: (response) => {
          console.log('Fetched tasks:', response);
          this.tasks.set(response as Task[]);
        },
        error: (error) => {
          console.error('Error fetching tasks', error);
        }
      });
    }
  }

  addTask() {
    this.router.navigate(['/create-task']);
  }

  getTasksByStatus(status: string) {
    this.taskService.getTasksByStatus(status).subscribe({
      next: (response) => {
        this.tasks.set(response as Task[]);
      },
      error: (error) => {
        console.error('Error fetching tasks by status', error);
      }
    });
  }

  onStatusChange(event: any) {
    const selectedStatus = event.target.value;
    if (selectedStatus) {
      this.getTasksByStatus(selectedStatus);
    }
    else {
      this.fillTask();
    }
  }

  deleteTask(task: Task) {
    if(task.id != undefined) {
      console.log('Deleting task:', task);
      this.taskService.deleteTask(task.id).subscribe({
        next: () => {
          this.tasks.set(this.tasks().filter(t => t.id !== task.id));
        },
        error: (error) => {
          console.error('Error deleting task', error);
        }
      });
    }
  }

  onSearch(event: any) {
    const searchTerm = event.target.value.toLowerCase();
    if (searchTerm) {
      this.taskService.getTasksByTitle(searchTerm).subscribe({
        next: (response) => {
          this.tasks.set(response as Task[]);
        },
        error: (error) => {
          console.error('Error fetching tasks by title', error);
        }
      });
    }
    else {
      this.fillTask();
    }
  }

  viewTask(task: Task) {
    this.router.navigate(['/view-task', task.id]);
  }
}
