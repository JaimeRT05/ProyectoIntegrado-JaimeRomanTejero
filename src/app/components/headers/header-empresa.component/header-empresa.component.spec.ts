import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderEmpresaComponent } from './header-empresa.component';

describe('HeaderEmpresa', () => {
  let component: HeaderEmpresaComponent;
  let fixture: ComponentFixture<HeaderEmpresaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderEmpresaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderEmpresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
