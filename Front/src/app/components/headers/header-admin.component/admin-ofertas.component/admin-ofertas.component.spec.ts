import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOfertasComponent } from './admin-ofertas.component';

describe('AdminOfertasComponent', () => {
  let component: AdminOfertasComponent;
  let fixture: ComponentFixture<AdminOfertasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminOfertasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminOfertasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
