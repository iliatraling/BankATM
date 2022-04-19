import { TestBed } from '@angular/core/testing';

import { BankaccService } from './bankacc.service';

describe('BankaccService', () => {
  let service: BankaccService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankaccService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
