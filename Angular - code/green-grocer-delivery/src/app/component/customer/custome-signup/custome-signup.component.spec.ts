import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomeSignupComponent } from './custome-signup.component';

describe('CustomeSignupComponent', () => {
  let component: CustomeSignupComponent;
  let fixture: ComponentFixture<CustomeSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CustomeSignupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomeSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
