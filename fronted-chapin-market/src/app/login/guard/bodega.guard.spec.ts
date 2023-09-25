import { TestBed } from '@angular/core/testing';

import { BodegaGuard } from './bodega.guard';

describe('BodegaGuard', () => {
  let guard: BodegaGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(BodegaGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
