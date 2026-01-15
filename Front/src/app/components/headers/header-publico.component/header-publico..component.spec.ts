import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderPublico } from './header-publico.component';

describe('HeaderPublico', () => {
  let component: HeaderPublico;
  let fixture: ComponentFixture<HeaderPublico>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderPublico]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderPublico);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
