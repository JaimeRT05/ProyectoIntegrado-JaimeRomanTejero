// ciudadano-dto.model.ts
export interface CiudadanoEditar {
  idCiudadano?: number;       // Opcional para nuevos registros
  nombre: string;
  apellidos: string;
  fechaNacimiento?: string;   // No editable, opcional en front
  calle: string;
  numero: number | null;
  localidad: string;
  codigoPostal: number | null;
  telefono: string;
  email?: string;             // No editable
  profesion: string;
  curriculumURL?: string;     // Por ahora no se usa
  nombreUsuario: string;
  validado?: boolean;         // No editable, opcional
}
