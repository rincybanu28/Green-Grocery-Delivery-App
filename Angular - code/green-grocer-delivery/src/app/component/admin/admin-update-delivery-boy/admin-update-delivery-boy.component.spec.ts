import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdateDeliveryBoyComponent } from './admin-update-delivery-boy.component';

describe('AdminUpdateDeliveryBoyComponent', () => {
  let component: AdminUpdateDeliveryBoyComponent;
  let fixture: ComponentFixture<AdminUpdateDeliveryBoyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminUpdateDeliveryBoyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminUpdateDeliveryBoyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
