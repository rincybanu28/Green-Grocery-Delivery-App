import { Component, ElementRef, OnInit, ViewChild ,AfterViewInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Supplier } from '../../../model/supplier';
import { SupplierService } from '../../../services/supplier.service';
import { ChangeDetectorRef } from '@angular/core';
// import { Component, ElementRef, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-admin-update-supplier',
  templateUrl: './admin-update-supplier.component.html',
  styleUrl: './admin-update-supplier.component.css'
})
export class AdminUpdateSupplierComponent implements OnInit {
  supplier: Supplier = new Supplier;
  
  supplierId:any;

  constructor(private supplierService:SupplierService, private router : Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.supplierId = this.route.snapshot.params[ 'supplierId' ];
    this.supplierService.findSupplierBySupplierId(this.supplierId).subscribe((data: Supplier) =>{
      this.supplier = data;
      console.log('Supplier details:', this.supplier);
    },(error: any) => console.log(error));
  }
 

  onSubmit(){
    this.supplierService.updateSupplier(this.supplierId, this.supplier).subscribe( data =>{
      //this.updateSupplierModal.nativeElement.modal('hide');
      this.supplier=data;
      this.goToSupplierList();
    },error => console.log(error));
  }
  
onUpdateClick(): void {
 
  console.log('Update button clicked');
  alert('Update Supplier Successfully');

}

    
    goToSupplierList():void{
      this.router.navigate(['/admin/supplier']);
    }

    close():void{
      this.router.navigate(['/admin/supplier']);
    }

}