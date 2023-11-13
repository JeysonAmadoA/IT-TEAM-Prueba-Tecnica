import { TestBed } from '@angular/core/testing';

import { PixabayStateService } from './pixabay-state.service';

describe('PixabayStateService', () => {
  let service: PixabayStateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PixabayStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
