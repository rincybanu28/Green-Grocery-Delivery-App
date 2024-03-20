import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from '../../../model/product';
import { Supplier } from '../../../model/supplier';
import { ProductService } from '../../../services/product.service';
import { SupplierService } from '../../../services/supplier.service';

@Component({
  selector: 'app-admin-addproduct',
  templateUrl: './admin-addproduct.component.html',
  styleUrl: './admin-addproduct.component.css'
})
export class AdminAddproductComponent implements OnInit{

  @Output() productCreated = new EventEmitter<Supplier>();
  product:Product=new Product();
  myForm!: FormGroup;
  productForm!: FormGroup;
  getCategoryList: any[] = [];
  suppliers: Supplier[] = [];
 // category: any = 0;
  constructor(private productService:ProductService,  
    private supservice: SupplierService, 
    private router:Router,
     private fb: FormBuilder)
      { 
      this.productForm = this.fb.group({

        productImage: ['', Validators.required],
      productName: ['', [Validators.required]],
      productQuantity: ['', [Validators.required]],
      price: ['', Validators.required],
     supplier:[null],
     category:[null],
     buyDate: [this.getCurrentDate()]
    
      });
    }

    getCurrentDate(): string {
      const today = new Date();
      const isoDate = today.toISOString().split('T')[0];
      return isoDate;
    }

    ngOnInit(): void {
      // this.pservice.isUserLoginPresent();
      this.getCategoryList = this.productService.getCategoryList();
     
      this.supservice.getSupplierList().subscribe(
        (suppliers: Supplier[]): void => {
          this.suppliers = suppliers;
        },
        (error) => {
          console.error('Error fetching suppliers :', error);
        }
      );
      
    }
    // goToStudentList()
    // {
    //   this.router.navigate(['/deliveryboyes'])
    // }
  onSubmit():void
  
  {

    if (!this.productForm.valid) {
      alert('Fill the Form');
      return;
    }
    
     if(this.productForm.valid)
     {
        console.log(this.productForm.value);
        this.productService.createProduct(this.productForm.value).subscribe
        (
         
          (response)=>{
            console.log("Product record is created successfully",response)
            alert("Product added sucessfully");
            this.router.navigate(['/admin/productlist']);
            let ref = document.getElementById('cancel')
            ref?.click();
            this.productForm.reset();  //to reset form data after submiting
            this.productCreated.emit(); 
           
         // this.goToStudentList();
          },
          (error)=>{
            alert("Failed to add record");
            console.log("Error while creating Product",error)
           
          }
        );
         
        // onReset():void{
        //   this.deliveryBoyForm.reset();
        // }E:\Project\Final-Project\green-grocer-delivery\src\app
     }
  }
}
