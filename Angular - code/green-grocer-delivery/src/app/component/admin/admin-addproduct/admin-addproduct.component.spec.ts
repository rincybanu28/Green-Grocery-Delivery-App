import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddproductComponent } from './admin-addproduct.component';

describe('AdminAddproductComponent', () => {
  let component: AdminAddproductComponent;
  let fixture: ComponentFixture<AdminAddproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAddproductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAddproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
