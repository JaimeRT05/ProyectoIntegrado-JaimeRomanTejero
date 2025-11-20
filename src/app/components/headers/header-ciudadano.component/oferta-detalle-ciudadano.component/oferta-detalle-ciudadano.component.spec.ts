import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertaDetalleCiudadanoComponent } from './oferta-detalle-ciudadano.component';

describe('OfertaDetalleCiudadanoComponent', () => {
  let component: OfertaDetalleCiudadanoComponent;
  let fixture: ComponentFixture<OfertaDetalleCiudadanoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertaDetalleCiudadanoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OfertaDetalleCiudadanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
