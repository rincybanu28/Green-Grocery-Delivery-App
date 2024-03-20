import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOrderlistComponent } from './admin-orderlist.component';

describe('AdminOrderlistComponent', () => {
  let component: AdminOrderlistComponent;
  let fixture: ComponentFixture<AdminOrderlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminOrderlistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminOrderlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
