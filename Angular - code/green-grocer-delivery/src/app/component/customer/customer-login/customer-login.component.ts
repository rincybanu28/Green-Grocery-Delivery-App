import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Customer } from '../../../model/customer';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrl: './customer-login.component.css'
})
export class CustomerLoginComponent implements OnInit  {

  customerFirstName: string = "";
  customerLastName: string = "";
  // customerEmail: string = "";
  customerPhoneNumber: string = "";
  pincode: string = "";
  customerAddress: string = "";
  // customerPassword: string = "";

  
 
  ngOnInit() {
    const sign_in_btn = document.querySelector<HTMLButtonElement>("#sign-in-btn");
    const sign_up_btn = document.querySelector<HTMLButtonElement>("#sign-up-btn");
    const sign_in_btn2 = document.querySelector<HTMLAnchorElement>("#sign-in-btn2");
    const sign_up_btn2 = document.querySelector<HTMLAnchorElement>("#sign-up-btn2");



    sign_up_btn?.addEventListener("click", () => {
      this.toggleMode('sign-up-mode');
    });

    sign_in_btn?.addEventListener("click", () => {
      this.toggleMode('sign-up-mode', false);
    });

    sign_up_btn2?.addEventListener("click", () => {
      this.toggleMode('sign-up-mode2');
    });

    sign_in_btn2?.addEventListener("click", () => {
      this.toggleMode('sign-up-mode2', false);
    });

    
  }

  toggleMode(mode: string, add: boolean = true) {
    const container = document.querySelector<HTMLDivElement>(".container");
    if (add) {
      container?.classList.add(mode);
    } else {
      container?.classList.remove(mode);
    }
  }





  errormessage: string='';
  customerEmail: string='';
  customerPassword:string='';
errormessagep:string='';
customer: Customer = new Customer();
  customerForm!: FormGroup;
  constructor(private customerService:CustomerService, 
              private route:Router, private fb: FormBuilder) { 
                this.customerForm = this.fb.group({
                  customerFirstName: ['', [Validators.required, Validators.maxLength(50)]],
                  customerLastName: ['', [Validators.required, Validators.maxLength(50)]],
                  customerEmail: ['', [Validators.required, Validators.email]],
                  customerPhoneNumber: ['', Validators.required],
                  pincode: ['', Validators.required],
                  customerAddress: ['', Validators.required],
                  customerPassword: ['', Validators.required]
                  // departmentName: ['', Validators.required]
                });
              }
  // constructor(
  //   private route:Router,
  //   private fb: FormBuilder,
  //   private customerService:CustomerService
  //   ){}
  
  gotourl(url: any): void {
    this.route.navigate(["/"+url]);
  }

  login(): void {
    if (this.customerEmail === "" || this.customerEmail === undefined) {
      this.errormessage = "Email And Password is required";
    }
    this.errormessage = "";
    const re = /\S+@\S+\.\S+/;
    if (!re.test(this.customerEmail)) {
      this.errormessage = "Email And Password required";
      return;
    }
    this.errormessage = "";
  
    if (this.customerPassword === "" || this.customerPassword === undefined) {
      this.errormessagep = "Password is blank";
      return;
    }
    this.errormessagep = '';
  
    const body: any = {
      "customerEmail": this.customerEmail,
      "customerPassword": this.customerPassword
    };
  
    this.customerService.customerSignIn(body).pipe(take(1)).subscribe(
      (res: any) => {
        if (res && res.customerId) {
          alert("Login successful");
          if (res.role) {
            this.customerService.storeCustomerRole(res.role);
          }
          this.customerService.storeCustomerAuthorization(res.customerId);
          let customerName = '';
          if (res.customerFirstName) {
            customerName += res.customerFirstName;
          }
          if (res.customerLastName) {
            customerName += ' ' + res.customerLastName;
          }
          this.customerService.storeCustomerName(customerName);
          console.log('Customer role:', res.role);
          console.log('Customer Name:', res.customerName);
  
          if (res.role === 'admin') {
            this.route.navigate(['/admin/home']);
          } else {
            this.route.navigate(['/customer/home']);
          }
        }
        else{
          alert("Something going wrong in login! pls try again");
        }
      },
      err => {
        alert("Customer email/password is invalid");
        console.log("Error ", err);
        console.log(">>> ", err);
        // if(err?.error && err?.error.startsWith("Customer  not found with")){
        //   alert("Customer email/password is invalid");
        // }
        // else{
        //   alert("Something going wrong in login! pls try again");
        // }
    }
    )
  } 


  



  onSubmit(): void {
    const body: any = {
      customerFirstName : this.customerFirstName,
      customerLastName : this.customerLastName,
      customerPhoneNumber :this.customerPhoneNumber,
      pincode : this.pincode,
      customerEmail : this.customerEmail,
      customerAddress: this.customerAddress,
      customerPassword :this.customerPassword,
     role: "customer" 
     
    }
    if (this.customerForm.valid) {

      console.log("=======>",body);
      this.customerService.createCustomer(body).pipe(take(1)).subscribe((res :any) => {
        console.log("*****",res);
        if(res && res?.customerId){
          alert("Registration sucessful");
          this.route.navigate(["/login"]);
        }
      }, err =>{
          console.log("Error  ", err);
          if (err && err?.error === 'Oops duplicate Entry of the data !') {
            alert("Email address registered already, please go to login.");
          } else {
            alert("Something going wrong..pls try again");
          }
      })
}
  }

}
