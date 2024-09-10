import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,HomeComponent,HttpClientModule,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
  
})
export class AppComponent implements AfterViewInit {
  title = 'frontend';
constructor(private router:Router){}
  ngAfterViewInit(): void {
    this.navigateToHome();
  }
  navigateToHome(){
this.router.navigate(['home']);
  }


}
