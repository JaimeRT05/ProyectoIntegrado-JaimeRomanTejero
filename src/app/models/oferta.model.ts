// Para la lista de ofertas (solo los datos necesarios)
export interface Oferta {
  idOferta: number;
  titulo: string;
  tipoOferta: string;
  perfilProfesional: string;
  fechaPublicacion: string;
}

// Para el detalle de una oferta (todos los datos + empresa)
export interface OfertaDetalle {
  idOferta: number;
  titulo: string;
  descripcion: string;
  perfilProfesional: string;
  tipoOferta: string;
  duracion: string;
  codigoOferta: string;
  fechaPublicacion: string;
  estado: string;
  nombreEmpresa: string;
  actividadEmpresa: string;
}
