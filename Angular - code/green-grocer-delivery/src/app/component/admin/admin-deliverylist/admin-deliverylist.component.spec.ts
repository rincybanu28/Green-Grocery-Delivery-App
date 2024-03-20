import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDeliverylistComponent } from './admin-deliverylist.component';

describe('AdminDeliverylistComponent', () => {
  let component: AdminDeliverylistComponent;
  let fixture: ComponentFixture<AdminDeliverylistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminDeliverylistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminDeliverylistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
