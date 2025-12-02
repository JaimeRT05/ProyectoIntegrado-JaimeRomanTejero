import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { RegistroService } from '../../services/registro.service';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';
import {Usuario} from '../../models/usuario.model'
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-registro',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login-registro.component.html',
  styleUrls: ['./login-registro.component.css']
})
export class LoginRegistroComponent {
  @Output() cerrar = new EventEmitter<void>();

  // Login
  nombreUsuario = '';
  contrasenaUsuario = '';
  loginError = '';

  // Modal registro
  mostrarRegistro = false;
  tipoRegistro: 'CIUDADANO' | 'EMPRESA' | '' = '';

  // Ciudadano
  curriculumFile: File | null = null;
  ciudadano = {
    nombreUsuario: '',
    contrasenaUsuario: '',
    repetirContrasena: '',
    nombre: '',
    apellidos: '',
    fechaNacimiento: '',
    calle: '',
    numero: null,
    localidad: '',
    codigoPostal: null,
    telefono: '',
    email: '',
    profesion: '',
    curriculumURL: ''
  };

  // Empresa
  empresa = {
    nombreUsuario: '',
    contrasenaUsuario: '',
    repetirContrasena: '',
    nombreCompleto: '',
    cif: '',
    actividadEmpresa: '',
    calle: '',
    numero: null,
    poligono: '',
    localidad: '',
    codigoPostal: null,
    telefono: '',
    email: '',
    direccionWeb: ''
  };

  constructor(
  private loginService: LoginService,
  private registroService: RegistroService,
  private authService: AuthService, 
  private usuarioService: UsuarioService,
  private router: Router 
) {}


  // Cerrar modal
  closeModal() {
    this.cerrar.emit();
    this.tipoRegistro = '';
  }

login() {
  this.loginError = '';
  this.loginService.login({
    nombreUsuario: this.nombreUsuario,
    contrasenaUsuario: this.contrasenaUsuario
  })
  .subscribe({
    next: res => {
      // Guardar token en localStorage usando AuthService
      this.authService.login(res.token);

      // Guardar usuario en el servicio
      const usuario: Usuario = { 
        nombreUsuario: res.nombreUsuario, 
        perfil: res.perfil as 'CIUDADANO' | 'EMPRESA' | 'ADMIN' 
      }; 
      this.usuarioService.setUsuario(usuario);

      // Cerrar modal
      this.closeModal();  

      // Redirigir según perfil
      if (usuario.perfil === 'CIUDADANO') {
        this.router.navigateByUrl('/ciudadano/ofertas'); // navega correctamente
      } else if (usuario.perfil === 'EMPRESA') {
        this.router.navigateByUrl('/empresa/ofertas');
      } else {
        this.router.navigateByUrl('/');
      }


      alert('Login correcto');
    },
    error: () => this.loginError = 'Usuario o contraseña incorrectos'
  });
}


  // Seleccionar tipo de registro
  seleccionarTipo(tipo: 'CIUDADANO' | 'EMPRESA') {
    this.tipoRegistro = tipo;
  }

  // Archivo CV
  handleFileInput(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.curriculumFile = input.files[0];
      this.ciudadano.curriculumURL = this.curriculumFile.name;
    }
  }

  // Registrar Ciudadano
  submitCiudadano() {
    if (this.ciudadano.contrasenaUsuario !== this.ciudadano.repetirContrasena) {
      alert('Las contraseñas no coinciden');
      return;
    }

    const formData = new FormData();
    Object.entries(this.ciudadano).forEach(([key, value]) => {
      if (key !== 'repetirContrasena' && key !== 'curriculumURL') {
        formData.append(key, value as any);
      }
    });
    if (this.curriculumFile) {
      formData.append('curriculum', this.curriculumFile, this.curriculumFile.name);
    }
    formData.append('perfil', 'CIUDADANO'); // Perfil automático

    this.registroService.crearCiudadano(formData).subscribe({
      next: res => {
        alert('Ciudadano registrado correctamente');
        this.tipoRegistro = '';
      },
      error: err => {
        console.error(err);
        alert('Error al registrar ciudadano');
      }
    });
  }

  // Registrar Empresa
  submitEmpresa() {
    if (this.empresa.contrasenaUsuario !== this.empresa.repetirContrasena) {
      alert('Las contraseñas no coinciden');
      return;
    }

    const formData = new FormData();
    Object.entries(this.empresa).forEach(([key, value]) => {
      if (key !== 'repetirContrasena') formData.append(key, value as any);
    });
    formData.append('perfil', 'EMPRESA'); 

    this.registroService.crearEmpresa(formData).subscribe({
      next: res => {
        alert('Empresa registrada correctamente. Esperando validación del administrador.');
        this.tipoRegistro = '';
      },
      error: err => {
        console.error(err);
        alert('Error al registrar empresa');
      }
    });
  }
}
