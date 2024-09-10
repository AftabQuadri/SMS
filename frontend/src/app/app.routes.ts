import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { compileFunction } from 'vm';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    {
        path:'home',
        component:HomeComponent    
    }
];
