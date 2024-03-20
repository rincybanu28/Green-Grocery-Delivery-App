import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomeOrderComponent } from './custome-order.component';

describe('CustomeOrderComponent', () => {
  let component: CustomeOrderComponent;
  let fixture: ComponentFixture<CustomeOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CustomeOrderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomeOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
