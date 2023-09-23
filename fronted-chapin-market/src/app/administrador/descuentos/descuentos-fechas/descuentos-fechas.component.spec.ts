import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DescuentosFechasComponent } from './descuentos-fechas.component';

describe('DescuentosFechasComponent', () => {
  let component: DescuentosFechasComponent;
  let fixture: ComponentFixture<DescuentosFechasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DescuentosFechasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DescuentosFechasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
