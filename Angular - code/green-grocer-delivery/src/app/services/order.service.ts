import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { Delivery } from '../model/delivery';
import { Order } from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient:HttpClient) { }
  
  private baseURL = "http://localhost:8080";

  placeOrder(cid:any,cartid:any,body:any):Observable<any> {
    return this.httpClient.post(this.baseURL + "/api/orders/"+cid+"/"+cartid, body);
  }

  orderList(id:any):Observable<any>{
    return this.httpClient.get(this.baseURL+"/api/orders/"+id);
  }

  getAllorderList():Observable<any>{
    return this.httpClient.get(this.baseURL+"/api/orders/findAll/");
  }

  placeOrderProduct(cid:any, body:any):Observable<any>{
    return this.httpClient.post(this.baseURL + "/api/orders/addOrder/"+cid, body);
  }
  addPayment(body:any,orderid:any,cid:any):Observable<any> {
    return this.httpClient.post(this.baseURL + "/api/payements/"+orderid+"/"+cid, body);
  }
  getOrderDetailsbyId(orderId: number): Observable<Order> {
    return this.httpClient.get<Order>(`${this.baseURL}/api/orders/findByOrderId/${orderId}`);
  }

  
}


