
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MainContainerComponent } from './components/main-container.component/main-container.component';
import { HeaderContainerComponent } from './components/headers/header-container.component/header-container.component';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FontAwesomeModule, RouterModule, MainContainerComponent, HeaderContainerComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {}