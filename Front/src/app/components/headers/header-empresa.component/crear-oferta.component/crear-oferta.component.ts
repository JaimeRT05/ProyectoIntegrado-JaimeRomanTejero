import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OfertaEmpresaService } from '../../../../services/oferta-empresa.service';
import { CrearOferta } from '../../../../models/oferta.model';

@Component({
  selector: 'app-crear-oferta',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './crear-oferta.component.html',
  styleUrls: ['./crear-oferta.component.css']
})
export class CrearOfertaComponent {

  ofertaForm: FormGroup;
  mensaje = '';
  codigoGenerado = ''; 

  tiposOferta = [
    { value: 'FORMACION_DUAL', label: 'Formación DUAL' },
    { value: 'PRACTICAS', label: 'Prácticas' },
    { value: 'CONTRATO', label: 'Contrato' }
  ];

  constructor(
    private fb: FormBuilder,
    private ofertaEmpresaService: OfertaEmpresaService
  ) {
    this.ofertaForm = this.fb.group({
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      perfilProfesional: ['', Validators.required],
      tipoOferta: ['', Validators.required],
      duracion: [''] 
    });
  }

  crearOferta() {
    if (this.ofertaForm.invalid) {
      this.mensaje = 'Rellena todos los campos obligatorios';
      return;
    }

    this.mensaje = '';
    this.codigoGenerado = '';

    const nuevaOferta: CrearOferta = this.ofertaForm.value;

    this.ofertaEmpresaService.crearOferta(nuevaOferta).subscribe({
      next: (data) => {
        this.mensaje = ' Oferta creada correctamente';
        this.codigoGenerado = data.codigoOferta; 
        this.ofertaForm.reset();
      },
      error: (err) => {
        console.error(err);
        this.mensaje = ' Error al crear la oferta';
      }
    });
  }

  volver() {
    window.history.back(); 
  }

}
