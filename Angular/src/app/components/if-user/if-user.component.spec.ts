import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IfUserComponent } from './if-user.component';

describe('IfUserComponent', () => {
  let component: IfUserComponent;
  let fixture: ComponentFixture<IfUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IfUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IfUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
