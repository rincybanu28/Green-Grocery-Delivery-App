import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  customerEmail: string= '';
  isShowChangePassword: boolean = false;
  newPassword: string = '';
  customer: any;

  constructor(
    private custservice: CustomerService,
    private route: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const body = {
      customerEmail: this.customerEmail
    };
    this.custservice.forgotPassword(body).pipe(take(1)).subscribe((res: { customerId: any; }) => {
      if (!!res && res?.customerId) {
        this.customer = res;
        this.isShowChangePassword = true;
      }
    }, err => {
      this.isShowChangePassword = false;
      alert("Customer not found . Please enter valid email.")
    });
  }

  onChangePassword(): void {
    this.customer.customerPassword = this.newPassword;
    this.custservice.changePassword(this.customer?.customerId,this.newPassword).pipe(take(1)).subscribe((res) => {
      if (res && res.customerId) {
        alert("Password changed successfully");
        this.route.navigate(["/customer-login"]);
      }
    }, error => {
      alert("Error occured while changing password.");
    });
  }
}
