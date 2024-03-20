import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAdddeliveryboyComponent } from './admin-adddeliveryboy.component';

describe('AdminAdddeliveryboyComponent', () => {
  let component: AdminAdddeliveryboyComponent;
  let fixture: ComponentFixture<AdminAdddeliveryboyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAdddeliveryboyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAdddeliveryboyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
