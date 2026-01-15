import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms';
import { OfertaDTO, OfertaEditarDTO } from '../../../../models/oferta.model';
import { OfertaEmpresaService } from '../../../../services/oferta-empresa.service';

@Component({
  selector: 'app-gestionar-ofertas',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './gestionar-oferta.component.html',
  styleUrls: ['./gestionar-oferta.component.css']
})
export class GestionarOfertasComponent implements OnInit {

  ofertas: OfertaDTO[] = [];
  editarOfertaId: number | null = null;
  editarForm: FormGroup;
  mensaje: string | null = null;

  constructor(private ofertaEmpresaService: OfertaEmpresaService, private fb: FormBuilder) {
    this.editarForm = this.fb.group({
      titulo: [''],
      descripcion: [''],
      perfilProfesional: [''],
      tipoOferta: [''],
      duracion: ['']
    });
  }

  ngOnInit(): void {
    this.cargarOfertas();
    this.comprobarOfertasPendientes();
  }

  cargarOfertas(): void {
    this.ofertaEmpresaService.listarOfertasEmpresa().subscribe({
      next: (data: OfertaDTO[]) => {
        this.ofertas = data;
      },
      error: (err: any) => {
        console.error('Error al cargar ofertas:', err);
        this.mensaje = 'Error al cargar ofertas';
      }
    });
  }

  finalizarOferta(oferta: OfertaDTO): void {
    this.ofertaEmpresaService.finalizarOferta(oferta.idOferta!).subscribe({
      next: () => {
        this.mensaje = `Oferta "${oferta.titulo}" finalizada correctamente`;
        this.cargarOfertas();
      },
      error: (err: any) => {
        console.error('Error al finalizar oferta:', err);
        this.mensaje = 'No se pudo finalizar la oferta';
      }
    });
  }

  eliminarOferta(oferta: OfertaDTO): void {
     if (!confirm(`¿Eliminar oferta ${oferta.titulo}?`)) return;

    this.ofertaEmpresaService.eliminarOferta(oferta.idOferta!).subscribe({
      next: () => {
        this.mensaje = `Oferta "${oferta.titulo}" eliminada correctamente`;
        this.cargarOfertas();
      },
      error: (err: any) => {
        console.error('Error al eliminar oferta:', err);
        this.mensaje = 'No se pudo eliminar la oferta';
      }
    });
  }

  editarOferta(oferta: OfertaDTO): void {
      this.editarOfertaId = oferta.idOferta!;
      this.editarForm.setValue({
        titulo: oferta.titulo || '',
        descripcion: oferta.descripcion || '',
        perfilProfesional: oferta.perfilProfesional || '',
        tipoOferta: oferta.tipoOferta || '',
        duracion: oferta.duracion || ''
      });
      this.mensaje = null;
    }

  guardarEdicion(): void {
    if (!this.editarOfertaId) return;

    const dto: OfertaEditarDTO = {
      titulo: this.editarForm.value.titulo,
      descripcion: this.editarForm.value.descripcion,
      perfilProfesional: this.editarForm.value.perfilProfesional,
      tipoOferta: this.editarForm.value.tipoOferta,
      duracion: this.editarForm.value.duracion
    };

    this.ofertaEmpresaService.modificarOferta(this.editarOfertaId, dto).subscribe({
      next: (ofertaActualizada: OfertaDTO) => {
        this.mensaje = `Oferta "${ofertaActualizada.titulo}" modificada correctamente`;
        this.editarOfertaId = null;
        this.editarForm.reset();
        this.cargarOfertas();
      },
      error: (err: any) => {
        console.error('Error al modificar oferta:', err);
        this.mensaje = 'No se pudo modificar la oferta';
      }
    });
  }



  cancelarEdicion(): void {
    this.editarOfertaId = null;
    this.editarForm.reset();
    this.mensaje = null;
  }


  volver(): void {
    window.history.back(); 
  }

  comprobarOfertasPendientes(): void {
  this.ofertaEmpresaService.ofertasPendientes().subscribe({
    next: (ofertas) => {
      if (ofertas.length > 0) {
        this.mensaje =
          `⚠️ Tienes ${ofertas.length} ofertas abiertas sin movimientos desde hace más de un mes`;
      }
    },
    error: () => {
      console.warn('No se pudo comprobar ofertas pendientes');
    }
  });
}

}
