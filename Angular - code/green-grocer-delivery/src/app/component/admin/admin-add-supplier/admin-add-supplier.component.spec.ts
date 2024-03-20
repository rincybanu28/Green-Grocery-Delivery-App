import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddSupplierComponent } from './admin-add-supplier.component';

describe('AdminAddSupplierComponent', () => {
  let component: AdminAddSupplierComponent;
  let fixture: ComponentFixture<AdminAddSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAddSupplierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAddSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
