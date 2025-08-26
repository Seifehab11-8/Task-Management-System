import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';   
import { BasicAuthService } from '../basic-auth-service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  router = inject(Router);
  authService = inject(BasicAuthService);

  onClickLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  onClickHome() {
    this.router.navigate(['/dashboard']);
  }
}
