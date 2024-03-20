import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { debounceTime, distinctUntilChanged, Observable, Subject, take } from 'rxjs';
import { Product } from '../../../model/product';

import { CustomerService } from '../../../services/customer.service';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrl: './customer-home.component.css'
})
export class CustomerHomeComponent  implements OnInit {

 
  productList1:Array<Product>=[];
  productList:Array<Product>=[];
  quantity: number = 0;
  customer: any = {};
  getCategoryList: any[] = [];
  category: any = 100;
  allProductList : Array<Product>= [];
  offset: number = 0;
  pageSize: number = 4; // How many item you want to display in your page.
  totalproduct: number = 1;
  searchType: string = "bycategory";
  searchKeyword: string = "";
  customerInputUpdate = new Subject<string>();


  constructor(
    private productService: ProductService,
    private customerService:CustomerService,
    private router: Router,
    private snakcbar: MatSnackBar
  ) {
   this.customerService.isCustomerLoginPresent();
    this.getProductList(true);
    this.getCustomerDetail();
   
      
       

     
  }

  // ngOnInit(): void {
  //   this.fetchProductList();
  //   this.getCategoryList = this.productService.getCategoryList();
  //   this.customerInputUpdate.pipe(
  //     debounceTime(400),
  //     distinctUntilChanged())
  //     .subscribe(value => {
  //       if (value.length > 0) {
  //         this.productService.searchProductByName(this.searchKeyword, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize).pipe((take(1))).subscribe((res: any) => {
  //           if (res && res?.product && Array.isArray(res?.product)) {
  //             console.log(res);
  //             this.productList = res?.product;
  //             this.allProductList = res?.product;
  //             this.totalproduct = res?.totalProduct;
  //           }
  //         });
  //       } else {
  //         this.productList = [];
  //       }
        
  //     });
  // }


  ngOnInit(): void {
    this.fetchProductList();
    this.getCategoryList = this.productService.getCategoryList();
    this.customerInputUpdate.pipe(
      debounceTime(400),
      distinctUntilChanged())
      .subscribe(value => {
        if (value.length > 0) {
          this.productService.searchProductByName(value, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize)
            .pipe(take(1))
            .subscribe((res: any) => {
              if (res && res.products && Array.isArray(res.products)) {
                console.log(res);
                this.productList = res.products;
                this.allProductList = res.products;
                this.totalproduct = res.totalProducts;
              }
            });
        } else {
          this.productList = [];
        }
      });
    


  }

  fetchProductList(){

    this.productService.getProductlist().subscribe(
      (data:Product[])=>{
        // console.log(data);
        this.productList1=data;
      },
      (error)=>{
        console.log("error");
      }
    )
     

      }
    
  



  getCustomerDetail(): void {
    const cid = this.customerService.getCustomerAuthorization();
    console.log(cid);
    this.customerService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Customer***", res);
        if (!!res && res?.customerId) {
          this.customer = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }

  // getProductList(isAllProduct: boolean = false): void {
  //   let product: any = this.productService.getAllProducts(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
  //   console.log(product);
  //   if (!isAllProduct) {
  //     product = this.productService.getProductByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    
  //   }
  //   product.pipe(take(1)).subscribe((res: any) => {
  //     console.log('All Products :', res);
  //     if (res && res?.product && Array.isArray(res?.product)) {
  //       console.log('All Products :',product);
  //       this.productList = res?.product;
  //       console.log('All Products :',product);

  //       this.allProductList = res?.product;
  //       console.log('All Products :',product);
  //       this.totalproduct = res?.product;
  //     }
  //   }, (err: any) => {
  //     console.log("Error");
  //   });
  // }




  getProductList(isAllProduct: boolean = false): void {
    let productObservable: Observable<any>;
  
    if (isAllProduct) {
      productObservable = this.productService.getAllProducts(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    } else {
      productObservable = this.productService.getProductByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    }
  
    productObservable.pipe(take(1)).subscribe(
      (res: any) => {
        console.log('Response from API:', res);
  
        if (res && res.products && Array.isArray(res.products)) {
          this.productList = res.products;
          this.allProductList = res.products;
          this.totalproduct = res.totalProducts;
        } else {
          console.error('Invalid API response structure:', res);
          // You might want to handle this case based on your application's requirements.
        }
      },
      (err: any) => {
        console.error('Error fetching product list:', err);
        // Handle error based on your application's requirements.
      }
    );
  }
  




  // product?.productId.toString());  const element: any = document.getElementById(book?.bookId.toString());
  addToCart(product: Product): void {
    const element:any=document.getElementById(product?.productId.toString());
  let qty:any= element!==null ? element.value : 0; 
  if(qty ===""){
    element.value=0;
    qty=0;
  }
    if (qty === 0 || qty === "0" || qty <0) {
      alert("Qunatity must be more than zero");
      return ;
    }

    if (qty > product?.productQuantity) {
      alert('Added quantity should not greater than available quantity');
      return;
    }
    
    const body: any = {
      quantity: qty,
      mrpPrice: product?.price,
      // boook: book,
      product1: product,
      customer: this.customer
    };
    console.log("add to cart", body);
    this.productService.addToCart(body, product?.productId, this.customer?.customerId).pipe(take(1)).subscribe(
      (res: any) => {
        console.log(res);
        if (!!res && res?.cartId) {
        alert("Product added sucessfully");
          this.getProductList(true);
          
          
        }
      }, err => {
        console.log("Error");
      }
      
    )
    
  }


  getProductByCategory(): void {
    this.offset = 0;
    this.totalproduct = 1;
    if (this.category === "100") {
      this.getProductList(true);
    } else {
      this.getProductList(false);
    }
  }


  onNextPageClick(pageOffSet: any): void {
    this.offset = pageOffSet;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void {
    this.offset -= 1;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void {
    this.offset = 0;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void {
    const lastPage = Math.ceil(this.totalproduct / this.pageSize);
    this.offset = lastPage;
    this.getProductList(this.category === 100 || this.category === "100");
  }
  gotocartList(): void {
    this.router.navigate(["/customer/cart"]);
  }

  getSelectedType(event: any): void {
    this.searchType = event?.value;
    if (this.searchType === "bysearch") {
      this.productList1 = [];
    } else {
      //All category dropdown
      this.getProductList(true);
    }
  }

  getSearchWord(ev: any): void {
    setTimeout(() => {
      this.customerInputUpdate.next(this.searchKeyword);
    }, 100);
  }



}
