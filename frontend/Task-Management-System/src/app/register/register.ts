import { Component, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { RegisterService } from '../register-service';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  public loginlink = '/login';
  registrationForm = new FormGroup({
        username: new FormControl('', [Validators.required, Validators.minLength(3)]),
        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', [
            Validators.required,
            Validators.minLength(6),
        ]),
        age: new FormControl('',[Validators.required, Validators.min(18)])
    });
    constructor(private registerService: RegisterService) {}

    isPopupVisible = signal(false);

    get username() {
        return this.registrationForm.get('name');
    }
    get email() {
        return this.registrationForm.get('email');
    }
    get password() {
        return this.registrationForm.get('password');
    }

    get age() {
        return this.registrationForm.get('age');
    }

    onSubmit() {
        if (this.registrationForm.valid) {
            this.isPopupVisible.set(true); 
            this.registerService.registerUser(this.registrationForm.value).subscribe({
                next: (response) => {
                    console.log('Registration successful', response);
                },
                error: (error) => {
                    console.error('Registration failed', error);
                }
            });
            console.log('Form Submitted', this.registrationForm.value);
        } else {
            console.log('Form is invalid');
        }
    }

    closePopup() {
        this.isPopupVisible.set(false);
    }
}
