import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCiudadanosComponent } from './admin-ciudadanos.component';

describe('AdminCiudadanosComponent', () => {
  let component: AdminCiudadanosComponent;
  let fixture: ComponentFixture<AdminCiudadanosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminCiudadanosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCiudadanosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
