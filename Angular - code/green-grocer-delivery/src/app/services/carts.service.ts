import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartsService {
  url: string = 'http://localhost:8080';
  constructor(
    private http:HttpClient,
    private route:Router
  ) { }

  addToCart(body: any,pid:any,cid:any):Observable<any>{
    return this.http.post(this.url+"/api/carts/"+cid+"/"+pid,body);
  }


  cartList():Observable<any>{
    return this.http.get(this.url+"/api/carts/showAll");
  }

  placeOrder(cid:any,cartid:any,body:any):Observable<any> {
    return this.http.post(this.url + "/api/orders/"+cid+"/"+cartid, body);
  }

  deleteCart(id :any):Observable<any> {
    
    return this.http.delete(`${this.url}/api/carts/${id}`);
  }
  
  orderList(id:any):Observable<any>{
    return this.http.get(this.url+"/api/orders/"+id);
  }


  getAllorderList():Observable<any>{
    return this.http.get(this.url+"/api/orders/findAll/");
  }

  // placeOrderItem(cid:any, body:any):Observable<any>{
  //   return this.http.post(this.url + "/api/orders/addOrder/"+cid, body);
  // }

  placeOrderItem(cid: any, body: any): Observable<any> {
    return this.http.post<any>(this.url + "/api/orders/addOrder/" + cid, body);
  }

  addPaymentOfOrder(amount: any):Observable<any> {
    return this.http.get(this.url + "/api/orders/createTransaction/"+amount);
  }
  
  placeOrderProduct(cid:any, body:any):Observable<any>{
    return this.http.post(this.url + "/api/orders/addOrder/"+cid, body);
  }

}
