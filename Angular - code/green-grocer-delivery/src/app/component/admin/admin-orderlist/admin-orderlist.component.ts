import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../../../model/customer';
import { Delivery } from '../../../model/delivery';
import { Order } from '../../../model/order';
import { CustomerService } from '../../../services/customer.service';
import { DeliveryService } from '../../../services/delivery.service';
import { OrderService } from '../../../services/order.service';

@Component({
  selector: 'app-admin-orderlist',
  templateUrl: './admin-orderlist.component.html',
  styleUrls: ['./admin-orderlist.component.css']
})
export class AdminOrderlistComponent implements OnInit {
  orderList: Order[] = [];
  orderId:number | undefined;
 
  customer: Customer[] | undefined;
  constructor(private orderService: OrderService ,private route :ActivatedRoute ,
    private router: Router,
    private customerService: CustomerService ) {}

  ngOnInit(): void {
    this.orderId=this.route.snapshot.params['orderId'];
    this.orderService.getAllorderList().subscribe(
      (response: any) => {
        console.log('Complete Server Response:', response);
        if (Array.isArray(response)) {
          this.orderList = response;
          console.log('Order List Length:', this.orderList.length);
        } else if (response && response.deliveryId) {
          this.orderList = [response];
          console.log('Order List Length:', this.orderList.length);
        } else {
          console.error('Invalid server response:', response);
        }
      },
      (error) => {
        console.error('Error fetching orders:', error);
      }
    );
    this.fetchSupplierList();
  }
  
  delivery(orderId: number | undefined): void {
    if (orderId !== undefined) {
      this.router.navigate(['/admin/delivery', orderId]);
    }
  }

  fetchSupplierList(): void {
    this.customerService.fetchAllCustomer().subscribe((data) => {
      this.customer = data;
      console.log(data);
    });
  }

  
}
