import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DeliveryBoy } from '../model/delivery-boy';

@Injectable({
  providedIn: 'root'
})
export class DeliveryBoyService {
  url: string = 'http://localhost:8080';

  constructor(
    private http:HttpClient,
    private route:Router
  ){}

  // addBook(body: any): Observable<any> {
  //   return this.http.post(this.url + "/api/books/addbooks", body);
  // }
  
    addBoy(body:any):Observable<any>{
      return this.http.post(this.url+"/api/deliveryBoy/register",body);
    }

  getAllDeliveryBoys(): Observable<DeliveryBoy[]> {

 return this.http.get<DeliveryBoy[]>(this.url+"/api/deliveryBoy/showAll");
  }

 
  deleteDeliveryBoy(deliveryBoyId: any): Observable<any> 
  {
    return this.http.delete(`${this.url}/api/deliveryBoy/delete/${deliveryBoyId}`);
  }

  getDeliveryBoyById(deliveryBoyId: number): Observable<DeliveryBoy>
  {
    return this.http.get<DeliveryBoy>(`${this.url}/api/deliveryBoy/findById/${deliveryBoyId}`);
  }

  getDeliveryBoyByEmail(deliveryBoyEmail:string): Observable<DeliveryBoy>
  {
    return this.http.get<DeliveryBoy>(`${this.url}/api/deliveryBoy/findByEmail/${deliveryBoyEmail}`);
  }


  updateDeliveryBoy(deliveryBoyId: any, deliveryBoy: DeliveryBoy): Observable<any> 
  {
    //console.log(deliveryBoyId);
    return this.http.put(`${this.url}/api/deliveryBoy/update/${deliveryBoyId}`,deliveryBoy);
  }

 

  
}
