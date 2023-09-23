import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucursalesTopComponent } from './sucursales-top.component';

describe('SucursalesTopComponent', () => {
  let component: SucursalesTopComponent;
  let fixture: ComponentFixture<SucursalesTopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SucursalesTopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SucursalesTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
