import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientesTopComponent } from './clientes-top.component';

describe('ClientesTopComponent', () => {
  let component: ClientesTopComponent;
  let fixture: ComponentFixture<ClientesTopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientesTopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientesTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
