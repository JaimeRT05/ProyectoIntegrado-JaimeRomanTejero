import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatosOfertaComponent } from './candidatos-oferta.component';

describe('CandidatosOfertaComponent', () => {
  let component: CandidatosOfertaComponent;
  let fixture: ComponentFixture<CandidatosOfertaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandidatosOfertaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandidatosOfertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
