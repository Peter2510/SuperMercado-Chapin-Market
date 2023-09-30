import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductosDisponiblesAgregarComponent } from './productos-disponibles-agregar.component';

describe('ProductosDisponiblesAgregarComponent', () => {
  let component: ProductosDisponiblesAgregarComponent;
  let fixture: ComponentFixture<ProductosDisponiblesAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductosDisponiblesAgregarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductosDisponiblesAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
