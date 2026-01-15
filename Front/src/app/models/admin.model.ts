export interface Ciudadano {
  idCiudadano: number;
  nombre: string;
  apellidos: string;
  fechaNacimiento: string;
  calle: string;
  numero: number;
  localidad: string;
  codigoPostal: number;
  telefono: string;
  email: string;
  profesion: string;
  curriculumURL?: string;
  validado: boolean;
}

export interface Empresa {
  idEmpresa: number;
  nombreCompleto: string;
  cif: string;
  actividadEmpresa: string;
  telefono: string;
  email: string;
  direccionWeb: string;
  localidad: string;
  calle: string;
  numero: number;
  codigoPostal: number;
  poligono: string;
  validado: boolean;
}


export interface Oferta {
  idOferta: number;
  titulo: string;
  descripcion: string;
  perfilProfesional: string;
  tipoOferta: string;
  duracion?: string;
  estado: string;
  idEmpresa: number;
}