import { DatePipe } from '@angular/common';
import { Component, NgZone, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
// import Razorpay from 'razorpay';
import { take } from 'rxjs';
import { Cart } from '../../../model/cart';
import { Order } from '../../../model/order';
import { CartsService } from '../../../services/carts.service';
import { CustomerService } from '../../../services/customer.service';
import { OrderService } from '../../../services/order.service';
import { CustomeOrderHistoryComponent } from '../custome-order-history/custome-order-history.component';

declare var Razorpay: any;

@Component({
  selector: 'app-custome-order',
  templateUrl: './custome-order.component.html',
  styleUrls: ['./custome-order.component.css'],
  providers: [DatePipe]
})
export class CustomeOrderComponent implements OnInit {
  orderList: Order[] = [];
  selectedOrder: Order | undefined;
  customer: any = {};
    Razorpay: any;
  constructor(
    private orderService: OrderService,
    private cartService: CartsService,
    private customerService: CustomerService,
    private router: Router,
    private datePipe: DatePipe,
    private dialog: MatDialog,
    private ngZone: NgZone
  ) {
    this.customerService.isCustomerLoginPresent();
  }

  ngOnInit(): void {
    this.getCustomerDetail();
    this.getOrderList();
  }

  getCustomerDetail(): void {
    const cid = this.customerService.getCustomerAuthorization();
    this.customerService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("User*****", res);
        if (!!res && res?.customerId) {
          this.customer = res;
        }
      },
      err => {
        console.log("Err");
      }
    );
  }

  getOrderList(): void {
    this.orderService.orderList(this.customerService.getCustomerAuthorization()).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("order*****", res);
        if (!!res && Array.isArray(res)) {
          this.orderList = res;
        }
      },
      err => {
        console.log("Error");
      }
    );
  }

  getDate(d: Date | string | undefined): string | null {
    if (d instanceof Date) {
      return this.datePipe.transform(d, 'shortDate') || null;
    } else if (typeof d === 'string') {
      const dateObj = new Date(d);
      return this.datePipe.transform(dateObj, 'shortDate') || null;
    }
    return null;
  }

  openHistory(order: Order): void {
    let dialogRef = this.dialog.open(CustomeOrderHistoryComponent, {
      data: order,
      maxWidth: '100vw',
      maxHeight: '100vh',
      height: '60%',
      width: '60%'
    });
  }

  addPayment(order: Order): void {
    this.cartService.addPaymentOfOrder(order?.totalPrice).pipe(take(1)).subscribe(
      (res: any) => {
        console.log('>>>>12333', res);
        console.log('****');
        if (res && res?.orderId) {
          console.log('okk');
          this.openTransactionModel(res);
          this.selectedOrder = order;
        }
      },
      error => {
        console.log("Error => ", error);
      }
    );
  }

  openTransactionModel(response: any) {
    var options = {
      order_id: response.orderId,
      key_id: response.key, // Include key_id property
      amount: response.amount,
      currency: response.currency,
      name: 'Green Grocery Delivery',
      description: 'Payment of Green Grocery Delivery',
      image: 'https://cdn.pixabay.com/photo/2023/01/22/13/46/swans-7736415_640.jpg',
      handler: (response: any) => {
        console.log('####', response);
        if (response != null && response.razorpay_payment_id != null) {
          this.processResponse(response);
        } else {
          alert("Payment failed..");
        }
      },
      prefill: {
        name: 'Priyanka Kadam',
        email: 'PRIYA@GMAIL.COM',
        contact: '8879084388'
      },
      notes: {
        address: 'Online Book store'
      },
      theme: {
        color: '#F37254'
      }
    };
    console.log(options);
    var razorPayObject = new Razorpay(options);
    razorPayObject.open();
  }

  processResponse(resp: any) {
    console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>', resp);
    if (resp && resp?.razorpay_order_id && resp?.razorpay_payment_id && this.selectedOrder) {
      const body: any = {
        totalPrice: this.selectedOrder?.totalPrice,
        orderId: this.selectedOrder?.orderId,
        PaidDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd')?.toString(),
        paidAmount: this.selectedOrder?.totalPrice,
        customer: this.customer
      };
      console.log("$$$$$$$", body);
      this.orderService.addPayment(body, this.selectedOrder?.orderId, this.customer?.customerId).pipe(take(1)).subscribe(
        (res: any) => {
          console.log("*********", res);
          if (res && res?.paymentId) {
            alert("Payment done successfully");
            this.ngZone.run(() => {
              this.getOrderList();
            });
          }
        },
        err => {
          console.log("error");
        }
      );
    }
  }
}
