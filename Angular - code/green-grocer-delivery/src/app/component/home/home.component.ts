import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/product';
import { Observable, Subject } from 'rxjs';
import { take } from 'lodash';
import { ProductService } from '../../services/product.service';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
   //  styleUrls: ['./home.component.css']

   
  productList1:Array<Product>=[];
  productList:Array<Product>=[];
  
  constructor(
    private productService: ProductService,
    private router: Router
    
  ) {
   
 
  }


  ngOnInit(): void {
    this.fetchProductList();
  
  }

  fetchProductList(){

    this.productService.getProductlist().subscribe(
      (data:Product[])=>{
        console.log(data);
        this.productList1=data;
      },
      (error)=>{
        console.log("error");
      }
    )
     

      }
    
  

    }