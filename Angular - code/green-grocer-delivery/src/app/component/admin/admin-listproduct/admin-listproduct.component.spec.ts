import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListproductComponent } from './admin-listproduct.component';

describe('AdminListproductComponent', () => {
  let component: AdminListproductComponent;
  let fixture: ComponentFixture<AdminListproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminListproductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminListproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
