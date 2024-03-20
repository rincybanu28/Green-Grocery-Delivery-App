import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Customer } from '../../../model/customer';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-custome-signup',
  templateUrl: './custome-signup.component.html',
  styleUrl: './custome-signup.component.css'
})
export class CustomeSignupComponent implements OnInit {
  customerFirstName: string = "";
  customerLastName: string = "";
  customerEmail: string = "";
  customerPhoneNumber: string = "";
  pincode: string = "";
  customerAddress: string = "";
  customerPassword: string = "";

  ngOnInit() {

    
  }

  errormessage: string='';
 
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

              onSubmit(): void {
                if (this.customerFirstName === '' || this.customerFirstName.length < 3) {
                  alert('FirstName must contain atleast 3 characters');
                  return;
                }
                if (this.customerLastName === '' || this.customerLastName.length < 3) {
                  alert('LastName must contain atleast 3 characters');
                  return;
                }
            
                if (this.customerPhoneNumber === '' || this.customerPhoneNumber.length < 10 || this.customerPhoneNumber.length > 10) {
                  alert('Phone must contain atleast 10 characters');
                  return;
                }
                const pattern=/^[6789][0-9]{9}$/;
                if (!pattern.test(this.customerPhoneNumber)) {
                  alert('Invalid mobile number.');
                  return;
                }
               
               
                if (this.pincode === '' || this.pincode.length < 6) {
                  alert('Pincode must contain atleast 6 characters');
                  return;
                }
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
                console.log(body);
               
            
                  console.log("=======>",body);
                  this.customerService.createCustomer(body).pipe(take(1)).subscribe((res :any) => {
                    // console.log("*****",res);
                    if(res && res?.customerId){
                      alert("Registration sucessful");
              this.route.navigate(["/customer-login"]);
                    }
                  }, err =>{
                      console.log("Error  ", err);
                      if (err && err?.error === 'Oops duplicate Entry of the data !') {
                        alert("Email address registered already, please go to login.");
                      } else {
                        alert("Ente valid data for all fields..pls try again");
                      }
                  })
          
              }


}
