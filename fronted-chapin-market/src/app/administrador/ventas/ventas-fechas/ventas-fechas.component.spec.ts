import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentasFechasComponent } from './ventas-fechas.component';

describe('VentasFechasComponent', () => {
  let component: VentasFechasComponent;
  let fixture: ComponentFixture<VentasFechasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VentasFechasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VentasFechasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
