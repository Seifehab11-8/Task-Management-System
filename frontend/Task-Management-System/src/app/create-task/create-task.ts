import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import { TaskService } from '../task-service';
import { Task } from '../model/task.model';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.html',
  styleUrl: './create-task.css',
  imports: [ReactiveFormsModule]
})
export class CreateTask implements OnInit{
  taskForm = new FormGroup({
    title:new FormControl('', [Validators.required]),
    description:new FormControl(''),
    status: new FormControl('', [Validators.required]),
    dueDate: new FormControl('', [Validators.required]),
  });
  constructor(private taskService: TaskService, private router: Router) {}

  ngOnInit(): void {
    if(sessionStorage.getItem('username') == null) {
      this.router.navigate(['/login']);
    }
  }

  get title() {
    return this.taskForm.get('title');
  }
  get description() {
    return this.taskForm.get('description');
  }
  get status() {
    return this.taskForm.get('status');
  }
  get dueDate() {
    return this.taskForm.get('dueDate');
  }

  onCreate() {
    if (this.taskForm.valid) {
      console.log(this.taskForm.value);
      const taskData: Task = {
        title: this.taskForm.value.title!, 
        description: this.taskForm.value.description || undefined,
        status: this.taskForm.value.status?.toLowerCase()!,
        dueDate: new Date(this.taskForm.value.dueDate!)
      };
      this.taskService.addTask(taskData).subscribe({
        next: () => {
          this.router.navigate(['/dashboard']);
        },
        error: (error) => {
          console.error('Error creating task:', error);
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
