import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-customer-header',
  templateUrl: './customer-header.component.html',
  styleUrl: './customer-header.component.css'
})
export class CustomerHeaderComponent {
  customerName: string = '';
  url: string = "/customer/home";
  constructor(private customerService :CustomerService,
    private router:Router
    ){
      if (this.customerService.getCustomerName() !== null) {
        this.customerName = this.customerService.getCustomerName();
        console.log('Customer Name:',this.customerName);
      }
    }

    routerToLink(link: string): void {
      console.log(link);
    if (link === '/customer/logout') {
      this.customerService.custoerLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
