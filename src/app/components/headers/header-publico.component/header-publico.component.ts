import { Component, OnInit, OnDestroy  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginRegistroComponent } from '../../login-registro.component/login-registro.component';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header-publico',
  standalone: true,
  imports: [CommonModule, LoginRegistroComponent, RouterModule],
  templateUrl: './header-publico.component.html',
  styleUrls: ['./header-publico.component.css']
})
export class HeaderPublicoComponent implements OnInit, OnDestroy {
  usuario: any = null;
  mostrarLogin = false;
  private sub!: Subscription;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.sub = this.authService.usuario$.subscribe(usuario => {
      this.usuario = usuario;
    });
  }

  ngOnDestroy() {
    if (this.sub) this.sub.unsubscribe();
  }

  // Mostrar modal login
  irALogin() {
    this.mostrarLogin = true;
  }

  // Cerrar modal login
  cerrarLogin() {
    this.mostrarLogin = false;
  }

  // Logout
  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  debug() {
  console.log('Click en Empresas');
  } 

}