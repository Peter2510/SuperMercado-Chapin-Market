import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostarProductosComponent } from './mostar-productos.component';

describe('MostarProductosComponent', () => {
  let component: MostarProductosComponent;
  let fixture: ComponentFixture<MostarProductosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostarProductosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostarProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
