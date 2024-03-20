import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Order } from '../../../model/order';
import { Product } from '../../../model/product';

@Component({
  selector: 'app-custome-order-history',
  templateUrl: './custome-order-history.component.html',
  styleUrl: './custome-order-history.component.css'
})
export class CustomeOrderHistoryComponent {


  order: Order | undefined;
  product: Array<Product> = [];
  constructor(
    //In constructor argument pass component class name i.e OrderHistoryDialogComponent
    public dialogRef: MatDialogRef<CustomeOrderHistoryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    console.log('>>>', data);
    if (!!data && data?.orderId) {
      this.order = data;
      if (this.order?.product && this.order?.product.length > 0) {
        this.product = this.order?.product;
      }
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
