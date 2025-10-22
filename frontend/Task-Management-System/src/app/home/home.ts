import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { BasicAuthService } from '../basic-auth-service';
@Component({
  selector: 'app-home',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit {
  public reglink = '/register'
  constructor(private authService: BasicAuthService, private router: Router) {}
  loginForm = new FormGroup ({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  ngOnInit() {
    if(sessionStorage.getItem('username') != null) {
      this.router.navigate(['/dashboard']);
    }
  }

  login() {
    const { username, password } = this.loginForm.value;
    if (username && password) {
      this.authService.getPrivate(username, password, '/tasks').subscribe({
        next: (response) => {
          console.log('Login successful', response);
          this.router.navigate(['/dashboard']);
        },
        error: (error) => {
          console.error('Login failed', error);
          sessionStorage.removeItem('username');
          sessionStorage.removeItem('password');
        }
      });
    }
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
