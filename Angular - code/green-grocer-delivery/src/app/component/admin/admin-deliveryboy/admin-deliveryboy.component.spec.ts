import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDeliveryboyComponent } from './admin-deliveryboy.component';

describe('AdminDeliveryboyComponent', () => {
  let component: AdminDeliveryboyComponent;
  let fixture: ComponentFixture<AdminDeliveryboyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminDeliveryboyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminDeliveryboyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
