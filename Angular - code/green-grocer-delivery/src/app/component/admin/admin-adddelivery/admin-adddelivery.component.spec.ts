import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAdddeliveryComponent } from './admin-adddelivery.component';

describe('AdminAdddeliveryComponent', () => {
  let component: AdminAdddeliveryComponent;
  let fixture: ComponentFixture<AdminAdddeliveryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAdddeliveryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAdddeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
