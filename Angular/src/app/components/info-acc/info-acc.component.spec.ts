import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoAccComponent } from './info-acc.component';

describe('InfoAccComponent', () => {
  let component: InfoAccComponent;
  let fixture: ComponentFixture<InfoAccComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoAccComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoAccComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
