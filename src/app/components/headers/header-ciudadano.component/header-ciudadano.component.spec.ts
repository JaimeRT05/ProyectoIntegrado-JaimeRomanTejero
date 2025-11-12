import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderCiudadanoComponent } from './header-ciudadano.component';

describe('HeaderCiudadano', () => {
  let component: HeaderCiudadanoComponent;
  let fixture: ComponentFixture<HeaderCiudadanoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderCiudadanoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderCiudadanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
