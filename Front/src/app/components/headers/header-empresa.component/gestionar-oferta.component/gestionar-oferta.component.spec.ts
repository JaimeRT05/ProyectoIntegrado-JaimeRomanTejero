import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarOfertaComponent } from './gestionar-oferta.component';

describe('GestionarOfertaComponent', () => {
  let component: GestionarOfertaComponent;
  let fixture: ComponentFixture<GestionarOfertaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionarOfertaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionarOfertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
