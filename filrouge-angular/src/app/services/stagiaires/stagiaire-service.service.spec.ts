import { TestBed } from '@angular/core/testing';

import { StagiaireServiceService } from './stagiaire-service.service';

describe('StagiaireServiceService', () => {
  let service: StagiaireServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StagiaireServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
