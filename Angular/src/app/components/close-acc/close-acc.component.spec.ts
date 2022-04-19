import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseAccComponent } from './close-acc.component';

describe('CloseAccComponent', () => {
  let component: CloseAccComponent;
  let fixture: ComponentFixture<CloseAccComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CloseAccComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CloseAccComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
