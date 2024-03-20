import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, tap } from 'rxjs';
import { Delivery } from '../model/delivery';
import { DeliveryBoy } from '../model/delivery-boy';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {

  url: string = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  getDeliveryList(): Observable<Delivery[]> {
    return this.httpClient.get<Delivery[]>(this.url + "/api/delivery/findAll");
  }
  
createDelivery(delivery:Delivery):Observable<Object>{
  return this.httpClient.post(this.url + "/api/delivery/register",delivery);
}
createDeiveryByOrder(delivery: Delivery, id: any): Observable<any> {
  return this.httpClient.post(`${this.url}/api/delivery/register/${id}`, delivery);
}
 
deleteDelivery(deliveryId : any):Observable<any>{
return this.httpClient.delete(`${this.url}/api/delivery/delete/${deliveryId}`);
}

}
// deleteStudent(id: any): Observable<any> {
//   return this.httpClient.delete(`${this.baseURL}/${id}`);
// }