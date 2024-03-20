import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Customer } from '../model/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url: string = 'http://localhost:8080';

  constructor(
    private http:HttpClient,
    private route:Router
  ){}


  //customer login
  customerSignIn(body: any): Observable<any> {
    return this.http.post(`${this.url}/api/customers/login/`, body);
  }

    //once we logged in that time we are storing Customer id into token 

    storeCustomerAuthorization(token: string): void {
      localStorage.setItem("customer", token);
    }      

    getCustomerAuthorization(): any {
      const token = localStorage.getItem("customer");
      return token;
    }
  storeCustomerRole(role: string): void {
    localStorage.setItem("role", role);
  }

  getCustomerRole(): any {
    const role = localStorage.getItem("role");
    return role;
  }

  storeCustomerName(customerName: string): void {
    localStorage.setItem("customerName", customerName);
  }

  getCustomerName(): any {
    const customerName = localStorage.getItem("customerName");
    return customerName;
  }

 
  
  getAdminAuthorization(): any {
    const token = localStorage.getItem("admin");
    return token;
  }

  custoerLogout(): void {
    localStorage.clear();
    this.route.navigate(['']);
  }
  
   // this is to get username in admin.home.html part via admin.home.ts
   isAdminLoginPresent(): void {
    if (this.getAdminAuthorization() === null) {
      this.route.navigate(['/admin-login']);
    }
  }

  isCustomerLoginPresent(): void {
    if (this.getCustomerAuthorization() === null) {
      this.route.navigate(['/login']);
    }
  }

  createCustomer(customer: Customer): Observable<Object>{
    return this.http.post(`${this.url}/api/customers/register`, customer);
  }

  adminLogout(): void {
    localStorage.clear();
    this.route.navigate(['/']);
  }




  getCustomerById(customerId:any):Observable<any> {
    return this.http.get(this.url + "/api/customers/findById/"+customerId);
  }

  //code for forgot password

  forgotPassword(body: any):Observable<any> {
    return this.http.post(this.url + "/api/customers/forgotpassword", body);
  }

  changePassword(custId: any,password:any):Observable<any> {
    console.log(custId);
    return this.http.post(this.url + "/api/customers/"+custId+"/"+password,{});
  }

 


  fetchAllCustomer(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.url + "/api/customers/showAll");
  }
  
}
