import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpresaService, CandidatoDTO } from '../../../../services/empresa.service';
import { CurriculumService } from '../../../../services/curriculum.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

interface OfertaDTO {
  idOferta: number;
  titulo: string;
  estado: 'ABIERTA' | 'CERRADA';
}

@Component({
  selector: 'app-candidatos-oferta',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './candidatos-oferta.component.html',
  styleUrls: ['./candidatos-oferta.component.css']
})
export class CandidatosOfertaComponent implements OnInit {

  ofertas: OfertaDTO[] = [];
  candidatos: CandidatoDTO[] = [];
  cargandoOfertas = true;
  cargandoCandidatos = false;

  ofertaSeleccionada?: OfertaDTO;
  idOfertaSeleccionada?: number;

  constructor(private empresaService: EmpresaService, private curriculumService: CurriculumService,private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.cargarOfertas();

    this.route.params.subscribe(params => {
    this.idOfertaSeleccionada = +params['idOferta']; 
    if (this.idOfertaSeleccionada) {
      this.cargarCandidatos(this.idOfertaSeleccionada);
    }
  });
  }

  cargarOfertas() {
    this.cargandoOfertas = true;
    this.empresaService.listarOfertasConPostulantes().subscribe({
      next: data => {
        this.ofertas = data;
        this.cargandoOfertas = false;

        // Selecciona automáticamente la primera oferta si hay
        if (this.ofertas.length > 0) {
          this.seleccionarOferta(this.ofertas[0]);
        }
      },
      error: err => {
        console.error('Error cargando ofertas', err);
        this.cargandoOfertas = false;
      }
    });
  }

  seleccionarOferta(oferta: OfertaDTO) {
    this.ofertaSeleccionada = oferta;
    this.cargarCandidatos(oferta.idOferta);
  }

  cargarCandidatos(idOferta: number) {
    this.cargandoCandidatos = true;
    this.empresaService.listarCandidatos(idOferta).subscribe({
      next: data => {
        this.candidatos = data;
        this.cargandoCandidatos = false;
      },
      error: err => {
        console.error('Error cargando candidatos', err);
        this.cargandoCandidatos = false;
      }
    });
  }

  descargarCV(idCurriculum?: number) {
    if (!idCurriculum) {
      alert('Este candidato no tiene curriculum.');
      return;
    }
    this.curriculumService.descargarCurriculum(idCurriculum).subscribe({
      next: blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `Curriculum_${idCurriculum}.pdf`;
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: err => console.error('Error al descargar CV', err)
    });
  }

  actualizarEstado(candidato: CandidatoDTO, estado: 'CONTRATADO' | 'RECHAZADO') {
    this.empresaService.actualizarEstado(candidato.idPostulacion, estado).subscribe({
      next: () => {

        candidato.estado = estado;

        if (estado === 'CONTRATADO' && this.ofertaSeleccionada) {
          this.ofertaSeleccionada.estado = 'CERRADA';
        }
      },
      error: err => console.error('Error al actualizar estado', err)
    });
  }

  cambiarEstado(postulacion: CandidatoDTO, nuevoEstado: 'CONTRATADO' | 'RECHAZADO') {
    this.empresaService.actualizarEstado(postulacion.idPostulacion, nuevoEstado)
        .subscribe({
            next: () => {
            
                postulacion.estado = nuevoEstado;
            },
            error: (err) => {
                console.error('Error al actualizar estado', err);
            }
      });
  }
}
