import { TestBed } from '@angular/core/testing';

import { BootcampServiceService } from './bootcamp-service.service';

describe('BootcampService', () => {
  let service: BootcampServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BootcampServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

