import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Task } from '../model/task.model';

@Component({
  selector: 'app-view-task',
  imports: [ReactiveFormsModule],
  templateUrl: './view-task.html',
  styleUrl: './view-task.css'
})
export class ViewTask implements OnInit {
  taskForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl(''),
    status: new FormControl('', [Validators.required]),
    dueDate: new FormControl('', [Validators.required]),
  });

  task?: Task;
  taskId?: number;

  constructor(
    private taskService: TaskService, 
    private router: Router, 
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    console.log('ViewTask component initialized');
    if(sessionStorage.getItem('username') == null) {
      this.router.navigate(['/login']);
    }
    const id = this.route.snapshot.params['id']; // Changed from paramMap.get('id')
    this.taskId = id ? +id : undefined;
    
    if (this.taskId) {
      this.loadTask();
    }
  }

  private loadTask() {
    this.taskService.getTaskById(this.taskId!).subscribe({
      next: (task) => {
        this.task = task;
        this.taskForm.patchValue({
          title: task.title,
          description: task.description || '',
          status: task.status,
          dueDate: this.formatDate(task.dueDate.toString()) // Convert Date to string for form
        });
      },
      error: (err) => console.error('Failed to load task:', err)
    });
  }

  private formatDate(dateString: string | null): string {
    return dateString ? new Date(dateString).toISOString().substring(0, 10) : '';
  }

  // Getters
  get title() { return this.taskForm.get('title'); }
  get description() { return this.taskForm.get('description'); }
  get status() { return this.taskForm.get('status'); }
  get dueDate() { return this.taskForm.get('dueDate'); }

  onClick() {
    if (!this.taskForm.valid || !this.taskId || !this.task) return;

    const updatedTask: Task = {
      ...this.task,
      title: this.taskForm.value.title!,
      description: this.taskForm.value.description || '',
      status: this.taskForm.value.status!,
      dueDate: new Date(this.taskForm.value.dueDate!)
    };

    this.taskService.updateTask(updatedTask).subscribe({
      next: () => this.router.navigate(['/dashboard']),
      error: (err) => console.error('Failed to update task:', err)
    });
  }
}
