import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit{
  customerName: string = '';
  constructor(
    private customerService: CustomerService
  ) {
    if (this.customerService.getCustomerName() !== null) {
      this.customerName = this.customerService.getCustomerName();
      console.log("*******",this.customerName);
    }
    this.customerService.isAdminLoginPresent();
  }

  ngOnInit(): void {
  }
}
