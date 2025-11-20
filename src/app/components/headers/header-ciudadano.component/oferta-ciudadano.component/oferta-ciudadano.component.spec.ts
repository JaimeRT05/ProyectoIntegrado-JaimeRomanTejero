import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertaCiudadanoComponent } from './oferta-ciudadano.component';

describe('OfertaCiudadanoComponent', () => {
  let component: OfertaCiudadanoComponent;
  let fixture: ComponentFixture<OfertaCiudadanoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertaCiudadanoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OfertaCiudadanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
