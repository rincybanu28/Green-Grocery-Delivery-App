import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../../../model/product';
import { Supplier } from '../../../model/supplier';
import { ProductService } from '../../../services/product.service';
import { SupplierService } from '../../../services/supplier.service';

@Component({
  selector: 'app-admin-update-product',
  templateUrl: './admin-update-product.component.html',
  styleUrl: './admin-update-product.component.css'
})
export class AdminUpdateProductComponent  implements OnInit{
  getCategoryList: any[] = [];
  suppliers: Supplier[] = [];
  product: Product = new Product;
  selectedSupplierId: number | undefined; // Temporary variable to hold the supplier ID
  productId:any;

  constructor(private productService:ProductService, 
    private supservice: SupplierService, 
     private router : Router,
              private route: ActivatedRoute) { }




              ngOnInit(): void {
                this.getCategoryList = this.productService.getCategoryList();
                this.supservice.getSupplierList().subscribe(
                  (suppliers: Supplier[]): void => {
                    this.suppliers = suppliers;
                  },
                  (error) => {
                    console.error('Error fetching suppliers:', error);
                  }
                );
              
                this.productId = this.route.snapshot.params['productId'];
                this.productService.getProductById(this.productId).subscribe((data: Product) => {
                  this.product = data;
                  this.selectedSupplierId = this.product.supplier?.supplierId; // Assign the supplier ID
                  console.log('Selected supplier ID:', this.selectedSupplierId); // Log the selected supplier ID
                }, (error: any) => console.error('Error fetching product:', error));
              }
              





//  
// onUpdateClick(){
//   this.productService.updateProduct(this.productId, this.product).subscribe( data =>{
//     //this.updateSupplierModal.nativeElement.modal('hide');
//     this.product=data;
//     this.goToProductList();
//   },error => console.log(error));
//   //console.log('Update button clicked');
//   alert('Update Product Records Successfully');

// }

    
onUpdateClick(): void {
  // Find the selected supplier from the suppliers list based on selectedSupplierId
  // const selectedSupplier = this.suppliers.find(supplier => supplier.supplierId === this.selectedSupplierId);
  

  const selectedSupplier = this.suppliers.find(supplier => {
    console.log('Supplier ID from suppliers:', supplier.supplierId);
    console.log('Selected supplier ID:', this.selectedSupplierId);
    return supplier.supplierId === this.selectedSupplierId;
  });

  // If a valid supplier is found, assign it to the product
  if (selectedSupplier) {
    this.product.supplier = selectedSupplier;

    // Make sure the category selected is valid and find its corresponding categoryId
    const selectedCategory = this.getCategoryList.find(category => category.name === this.product.category);
    if (selectedCategory) {
      // Assuming you have a categoryId property in your Product model
      this.product.category = selectedCategory.value; // Assign the categoryId to the product

      // Call the productService to update the product
      this.productService.updateProduct(this.productId, this.product).subscribe(
        (updatedProduct: Product) => {
          console.log(updatedProduct)
          // Update the product with the updated data returned from the backend
          this.product = updatedProduct;
          // Redirect to the product list page
          this.goToProductList();
          // Show success message
          alert('Product updated successfully');
        },
        (error) => {
          // Log and show error message if update fails
          console.error('Error updating product:', error);
          alert('Failed to update product');
        }
      );
    } else {
      // Handle error if selected category is not valid
      console.error('Selected category not found');
      alert('Selected category not found');
    }
  } else {
    // Handle error if selected supplier is not found
    console.error('Selected supplier not found');
    alert('Selected supplier not found');
  }
}






goToProductList():void{
      this.router.navigate(['/admin/product']);
    }

    close():void{
      this.router.navigate(['/admin/product']);
    }
}
