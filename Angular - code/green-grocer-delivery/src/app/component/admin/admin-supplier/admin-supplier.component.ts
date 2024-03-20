// admin-supplier.component.ts
import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Supplier } from '../../../model/supplier';
import { SupplierService } from '../../../services/supplier.service';
import { AdminAddSupplierComponent } from '../admin-add-supplier/admin-add-supplier.component';

@Component({
  selector: 'app-admin-supplier',
  templateUrl: './admin-supplier.component.html',
  styleUrls: ['./admin-supplier.component.css'],
})
export class AdminSupplierComponent implements OnInit {
  @ViewChild('addSupplierModal') addSupplierModal!: ElementRef;
  @Output() refreshPage: EventEmitter<void> = new EventEmitter<void>();
  supplier: Supplier = new Supplier();
  suppliers: Supplier[] | undefined;

  constructor(
    private supplierService: SupplierService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.fetchSupplierList();
  }

  reloadPage(): void {
    window.location.reload();
  }
  openModal(): void {
    const dialogRef = this.dialog.open(AdminAddSupplierComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(() => {
      this.fetchSupplierList();
    });
  }

  fetchSupplierList(): void {
    this.supplierService.getSupplierList().subscribe((data) => {
      this.suppliers = data;
    });
  }

  updateSupplier(supplierId: any): void {
    this.router.navigate(['/admin/update-supplier', supplierId]);
  }

  deleteSupplier(supplierId: any): void {
    this.supplierService.deleteSupplier(supplierId).subscribe(
      () => {
        // Handle success
        alert("Deleted Successfully");
        this.fetchSupplierList(); // Refresh the supplier list after successful deletion
        // Emit the event to refresh the page
        this.refreshPage.emit();
      },
      (error: HttpErrorResponse) => {
        // Handle error
        console.error("Error deleting supplier:", error);
        alert("Deleted Successfully");
        this.fetchSupplierList();
        this.refreshPage.emit();
      }
    );
  }
  
  
}
