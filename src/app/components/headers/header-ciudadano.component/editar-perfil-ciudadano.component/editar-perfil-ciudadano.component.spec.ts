import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarPerfilCiudadano } from './editar-perfil-ciudadano.component';

describe('EditarPerfilCiudadano', () => {
  let component: EditarPerfilCiudadano;
  let fixture: ComponentFixture<EditarPerfilCiudadano>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarPerfilCiudadano]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarPerfilCiudadano);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
