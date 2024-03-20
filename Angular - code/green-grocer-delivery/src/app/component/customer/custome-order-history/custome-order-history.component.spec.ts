import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomeOrderHistoryComponent } from './custome-order-history.component';

describe('CustomeOrderHistoryComponent', () => {
  let component: CustomeOrderHistoryComponent;
  let fixture: ComponentFixture<CustomeOrderHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CustomeOrderHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomeOrderHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
