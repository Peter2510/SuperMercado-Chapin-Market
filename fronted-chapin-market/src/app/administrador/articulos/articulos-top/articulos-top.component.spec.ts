import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticulosTopComponent } from './articulos-top.component';

describe('ArticulosTopComponent', () => {
  let component: ArticulosTopComponent;
  let fixture: ComponentFixture<ArticulosTopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticulosTopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticulosTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
