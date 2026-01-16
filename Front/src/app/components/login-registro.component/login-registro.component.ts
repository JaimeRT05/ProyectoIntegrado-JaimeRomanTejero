import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { RegistroService } from '../../services/registro.service';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';
import { CurriculumService } from '../../services/curriculum.service';
import { Usuario } from '../../models/usuario.model';
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

  // --- Login ---
  nombreUsuario = '';
  contrasenaUsuario = '';
  loginError = '';

  // --- Registro ---
  mostrarRegistro = false;
  tipoRegistro: 'CIUDADANO' | 'EMPRESA' | 'ADMIN' |'' = '';

  // Archivo currículum
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
    profesion: ''
  };

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

  admin = {
  nombreAdmin: '',
  contrasenaAdmin: '',
  repetirContrasenaAdmin: '',
  nombreUsuario: '',
  contrasenaUsuario: ''
};

  constructor(
    private loginService: LoginService,
    private registroService: RegistroService,
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private curriculumService: CurriculumService,
    private router: Router
  ) {}

  // ------------------- Login -------------------
  login() {
    this.loginError = '';
    this.loginService.login({
      nombreUsuario: this.nombreUsuario,
      contrasenaUsuario: this.contrasenaUsuario
    }).subscribe({
      next: res => {
        this.authService.login(res.token); // Guardar token

        const usuario: Usuario = {
          nombreUsuario: res.nombreUsuario,
          perfil: res.perfil as 'CIUDADANO' | 'EMPRESA' | 'ADMIN'
        };
        this.usuarioService.setUsuario(usuario);

        this.closeModal();

        // Redirección según perfil
        if (usuario.perfil === 'CIUDADANO') this.router.navigateByUrl('/ciudadano/ofertas');
        else if (usuario.perfil === 'EMPRESA') this.router.navigateByUrl('/empresa/ofertas');
        else this.router.navigateByUrl('/');

        alert('Login correcto');
      },
      error: () => this.loginError = 'Usuario o contraseña incorrectos'
    });
  }

  // ------------------- Registro -------------------
  seleccionarTipo(tipo: 'CIUDADANO' | 'EMPRESA' | 'ADMIN') {
    this.tipoRegistro = tipo;
  }

  // Manejo de archivo
  handleFileInput(event: any) {
    this.curriculumFile = event.target.files[0];
  }

  submitCiudadano() {
    if (this.ciudadano.contrasenaUsuario !== this.ciudadano.repetirContrasena) {
      alert('Las contraseñas no coinciden');
      return;
    }

    const formData = new FormData();

    // Añadir campos del ciudadano (excepto repetirContrasena)
    Object.entries(this.ciudadano).forEach(([key, value]) => {
      if (key !== 'repetirContrasena' && value != null) {
        formData.append(key, value as any);
      }
    });

    // Añadir currículum si existe
    if (this.curriculumFile) {
      formData.append('archivo', this.curriculumFile, this.curriculumFile.name);
    }

    formData.append('perfil', 'CIUDADANO');

    this.registroService.crearCiudadano(formData).subscribe({
      next: () => {
        alert('Ciudadano registrado correctamente');
        this.tipoRegistro = '';
        this.curriculumFile = null;
        this.ciudadano = { ...this.ciudadano, nombreUsuario: '', contrasenaUsuario: '', repetirContrasena: '', nombre: '', apellidos: '', fechaNacimiento: '', calle: '', numero: null, localidad: '', codigoPostal: null, telefono: '', email: '', profesion: '' };
      },
      error: err => {
        console.error(err);
        alert('Error al registrar ciudadano');
      }
    });
  }

  submitEmpresa() {
    if (this.empresa.contrasenaUsuario !== this.empresa.repetirContrasena) {
      alert('Las contraseñas no coinciden');
      return;
    }

    const formData = new FormData();
    Object.entries(this.empresa).forEach(([key, value]) => {
      if (key !== 'repetirContrasena' && value != null) formData.append(key, value as any);
    });
    formData.append('perfil', 'EMPRESA');

    this.registroService.crearEmpresa(formData).subscribe({
      next: () => {
        alert('Empresa registrada correctamente. Esperando validación del administrador.');
        this.tipoRegistro = '';
        this.empresa = { ...this.empresa, nombreUsuario: '', contrasenaUsuario: '', repetirContrasena: '', nombreCompleto: '', cif: '', actividadEmpresa: '', calle: '', numero: null, poligono: '', localidad: '', codigoPostal: null, telefono: '', email: '', direccionWeb: '' };
      },
      error: err => {
        console.error(err);
        alert('Error al registrar empresa');
      }
    });
  }
  
  submitAdmin() {
  if (this.admin.contrasenaAdmin !== this.admin.repetirContrasenaAdmin) {
    alert('Las contraseñas del administrador no coinciden');
    return;
  }

  const adminData = {
  nombreAdmin: this.admin.nombreAdmin,
  contrasenaAdmin: this.admin.contrasenaAdmin,
  nombreUsuario: this.admin.nombreUsuario,
  contrasenaUsuario: this.admin.contrasenaUsuario
};

  this.registroService.crearAdmin(adminData).subscribe({
    next: () => {
      alert('Administrador registrado correctamente');
      this.tipoRegistro = '';
      this.admin = {
        nombreAdmin: '',
        contrasenaAdmin: '',
        repetirContrasenaAdmin: '',
        nombreUsuario: '',
        contrasenaUsuario: ''
      };
    },
    error: err => {
      console.error(err);
      alert('Error al registrar administrador');
    }
  });
}

  // ------------------- Modal -------------------
  closeModal() {
    this.cerrar.emit();
    this.tipoRegistro = '';
  }

}