import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteDescuentosComponent } from './reporte-descuentos.component';

describe('ReporteDescuentosComponent', () => {
  let component: ReporteDescuentosComponent;
  let fixture: ComponentFixture<ReporteDescuentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReporteDescuentosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteDescuentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
