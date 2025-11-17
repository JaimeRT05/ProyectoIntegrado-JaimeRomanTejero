import { Component, OnInit, Input  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-main-container',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './main-container.component.html',
  styleUrls: ['./main-container.component.css']
})
export class MainContainerComponent implements OnInit {

constructor(private router: Router) { }
  @Input() childComponent: any;

    ngOnInit() {
    console.log('MainContainerComponent cargado');

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: any) => {
      console.log('Ruta actual:', event.urlAfterRedirects);
    });
  }




}
