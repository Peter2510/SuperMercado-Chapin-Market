import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisponiblesAgregarInventarioComponent } from './disponibles-agregar-inventario.component';

describe('DisponiblesAgregarInventarioComponent', () => {
  let component: DisponiblesAgregarInventarioComponent;
  let fixture: ComponentFixture<DisponiblesAgregarInventarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisponiblesAgregarInventarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisponiblesAgregarInventarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
