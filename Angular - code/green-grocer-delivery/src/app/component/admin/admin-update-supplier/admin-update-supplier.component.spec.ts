import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdateSupplierComponent } from './admin-update-supplier.component';

describe('AdminUpdateSupplierComponent', () => {
  let component: AdminUpdateSupplierComponent;
  let fixture: ComponentFixture<AdminUpdateSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminUpdateSupplierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminUpdateSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
