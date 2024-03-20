import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  url: string = 'http://localhost:8080';

  category: any = [{
    name: "FRUITS" , value: 0,
  }, {
    name: "VEGITABLES", value: 1,
  }
]
  constructor(private router :Router,
    private http:HttpClient
    ) { }


  getProductlist():Observable<any> {
    return this.http.get(this.url + "/api/products/findAll");
  }

  getAllProducts(offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/api/products/" + offset + "/" + limit);
  }

  getProductByCategory(categoryId: any, offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/api/products/" + categoryId + "/"+ offset + "/" + limit);
  }

  getCategoryList(): any {
    return this.category;
  }

  // searchProductByName(keyword: any, pageNo: any, pageSize: any):Observable<any> {
  //   return this.http.get(this.url + `/api/products/Search/${keyword}/${pageNo}/${pageSize}`);
  // }

  searchProductByName(keyword: string, pageNo: number, pageSize: number): Observable<any> {
    const url = `${this.url}/api/products/Search/${keyword}/${pageNo}/${pageSize}`;
    return this.http.get(url);
  }

  addToCart(body: any,pid:any,cid:any):Observable<any>{
    return this.http.post(this.url+"/api/carts/"+cid+"/"+pid,body);
  }


  createProduct(product: Product): Observable<Object>
  {
    return this.http.post(`${this.url}/api/products/create`,product);
  }

  deleteProduct(productId: any): Observable<any> 
  {
    return this.http.delete(`${this.url}/api/products/delete/${productId}`);
  }

  getProductById(productId: number): Observable<Product>
  {
    return this.http.get<Product>(`${this.url}/api/products/findById/${productId}`);
  }
  updateProduct(productId: any, product: Product): Observable<any> 
  {
    //console.log(deliveryBoyId);
    return this.http.put(`${this.url}/api/products/update/${productId}`,product);
  }

}
