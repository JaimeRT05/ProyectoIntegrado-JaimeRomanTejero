import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilCiudadanoComponent } from './perfil-ciudadano.component';

describe('PerfilCiudadanoComponent', () => {
  let component: PerfilCiudadanoComponent;
  let fixture: ComponentFixture<PerfilCiudadanoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerfilCiudadanoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerfilCiudadanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
