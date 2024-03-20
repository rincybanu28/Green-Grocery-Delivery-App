// admin-add-supplier.component.ts
import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Supplier } from '../../../model/supplier';
import { SupplierService } from '../../../services/supplier.service';

@Component({
  selector: 'app-admin-add-supplier',
  templateUrl: './admin-add-supplier.component.html',
  styleUrls: ['./admin-add-supplier.component.css'],
})
export class AdminAddSupplierComponent {
  @Output() supplierCreated = new EventEmitter<Supplier>();

  supplier: Supplier = new Supplier();
  supplierForm!: FormGroup;

  constructor(
    private supplierService: SupplierService,
    private router: Router,
    private fb: FormBuilder
   
  ) {
    this.supplierForm = this.fb.group({
      supplierName: ['', [Validators.required, Validators.maxLength(50)]],
      supplierEmail: ['', [Validators.required, Validators.email]],
      supplierPhoneNumber: ['', [Validators.required]],
      supplierAddress: ['', Validators.required],
      supplierCity: ['', Validators.required],
      supplierState: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (!this.supplierForm.valid) {
      alert('Fill the Form');
      return;
    }

    this.supplierService.createSupplier(this.supplierForm.value).subscribe(
      (response: any) => {
        alert('Supplier created successfully:');
        // alert('Supplier created successfully:');
        this.supplierCreated.emit(); // Emit event to indicate successful submission
      },
      (error: any) => {
        console.error('Error creating Supplier:', error);
        alert('Error creating Supplier. ');
      }
    );
  }
}
