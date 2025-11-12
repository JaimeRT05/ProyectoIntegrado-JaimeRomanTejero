import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
selector: 'app-header-admin',
standalone: true,
imports: [CommonModule, RouterModule],
templateUrl: './header-admin.component.html',
styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent {
@Input() usuario: any;           // Información del usuario si la necesitas
@Output() logout = new EventEmitter<void>();  // Para avisar al contenedor

cerrarSesion() {
this.logout.emit();  // Delegamos la acción al contenedor
}
}
