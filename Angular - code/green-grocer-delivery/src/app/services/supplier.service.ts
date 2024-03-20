import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Supplier } from '../model/supplier';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  constructor(private httpClient:HttpClient) { }

  baseURL: string = 'http://localhost:8080';
  
  createSupplier(supplier: Supplier): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/api/supplier/register`, supplier);
  }

  getSupplierList(): Observable<Supplier[]>{
    return this.httpClient.get<Supplier[]>(`${this.baseURL}/api/supplier/showAll`);
  }

  findSupplierBySupplierId(supplierId: any): Observable<Supplier>{
    console.log(supplierId);
    return this.httpClient.get<Supplier>(this.baseURL + "/api/supplier/findById/"+supplierId);
    //return this.httpClient.get<Supplier>(${this.baseURL}/findById/${supplierId});
  }

  updateSupplier(supplierId: any,supplier: Supplier): Observable<any> {
    return this.httpClient.put(`${this.baseURL}/api/supplier/update`, supplier);
  }

  deleteSupplier(supplierId: any): Observable<any> {
    return this.httpClient.delete(this.baseURL + "/api/supplier/delete/"+supplierId);
  }

  getSupplierByEmail(supplierEmail: string): Observable<any> {
    return this.httpClient.get(this.baseURL + "/api/supplier/findByEmail/"+supplierEmail);
  }


}
