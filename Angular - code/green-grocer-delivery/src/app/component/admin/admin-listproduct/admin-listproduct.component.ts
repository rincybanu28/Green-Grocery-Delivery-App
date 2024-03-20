import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Product } from '../../../model/product';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-admin-listproduct',
  templateUrl: './admin-listproduct.component.html',
  styleUrl: './admin-listproduct.component.css'
})
export class AdminListproductComponent {

  @ViewChild('addProductModal') addProductModal!: ElementRef;
  @Output() refreshPage: EventEmitter<void> = new EventEmitter<void>();

  openModal() {
    // Accessing the modal using ViewChild and ElementRef
    this.addProductModal.nativeElement.modal('show');
    
}
product: Product = new Product();

products: Product[] | undefined;



constructor(private productService:ProductService,
 private router: Router,
 private dialog: MatDialog ){}
ngOnInit():void  //this method is call whenever page is loaded
{
  
  let resp=this.productService.getProductlist();
  resp.subscribe((data)=>this.products=data);
  //console.log(resp);

}
reloadPage(): void {
  window.location.reload();
}


updateProduct(productId: any): void {
  if (this.router) {
      this.router.navigate(['/admin/update-product', productId]);
  } else {
      console.error('Router is not initialized.');
  }
}




deleteProduct(productId: any){
  this.productService.deleteProduct(productId).subscribe( data => {
    console.log(data);
    alert("Record Deleted sucessfully");
     // this.productService.getProductList();
     this.goToProductList();
     this.reloadPage();
     
  },
       (error: HttpErrorResponse) => {
        console.error("Error deleting supplier:", error);
         alert("Deleted Successfully");
         this.reloadPage();
         this.goToProductList();
       })
 }
 goToProductList():void{
   this.router.navigate(['/admin/product']);
 }
}