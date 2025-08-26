import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Register } from './register/register';
import { Dashboard } from './dashboard/dashboard';
import { CreateTask } from './create-task/create-task';
import { ViewTask } from './view-task/view-task';

export const routes: Routes = [
    {path:'', component:Home},
    {path:'register', component:Register},
    {path:'login', component:Home},
    {path:'dashboard', component:Dashboard},
    {path:'create-task',component:CreateTask},
    {path:'view-task/:id', component:ViewTask},
    {path:'**', redirectTo:'', component:Home},
];
