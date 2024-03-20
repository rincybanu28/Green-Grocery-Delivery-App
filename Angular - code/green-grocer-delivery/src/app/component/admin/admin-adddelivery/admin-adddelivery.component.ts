import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Delivery } from '../../../model/delivery';
import { DeliveryBoy } from '../../../model/delivery-boy';
import { Order } from '../../../model/order';
import { DeliveryBoyService } from '../../../services/delivery-boy.service';
import { DeliveryService } from '../../../services/delivery.service';
import { OrderService } from '../../../services/order.service';
import { AdminAdddeliveryboyComponent } from '../admin-adddeliveryboy/admin-adddeliveryboy.component';
@Component({
  selector: 'app-admin-adddelivery',
  templateUrl: './admin-adddelivery.component.html',
  styleUrls: ['./admin-adddelivery.component.css']
})
export class AdminAdddeliveryComponent implements OnInit {
  myForm!: FormGroup;
  orderId: any;
  delivery: Delivery = new Delivery();
  deliveryBoys: DeliveryBoy[] = [];
  orders: Order[] = [];
 
  constructor(
    private fb: FormBuilder,
    private deliveryService: DeliveryService,
    private deliveryBoyService: DeliveryBoyService,
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    
    this.deliveryBoyService.getAllDeliveryBoys().subscribe(
      (deliveryBoys: DeliveryBoy[]): void => {
        this.deliveryBoys = deliveryBoys;
      },
      (error) => {
        console.error('Error fetching delivery boys:', error);
      }
    );

    this.orderService.getAllorderList().subscribe(
      (orders: Order[]): void => {
        this.orders = orders;
      },
      (error) => {
        console.error('Error fetching orders:', error);
      }
    );

    this.route.params.subscribe(params => {
      this.orderId = +params['orderId'];
      this.orderService.getOrderDetailsbyId(this.orderId).subscribe(
        (orderDetails: Order) => {
          // Assuming the orderDetails include orderId
          this.orderId = orderDetails.orderId;
          console.log('Order details:', orderDetails);
          // Patch the form with the orderId
          this.myForm.patchValue({
            orderId: this.orderId,
            order: {
              orderId: orderDetails.orderId,
              orderStatus: orderDetails.orderStatus,
              price: orderDetails.mrpPrice,
              quantity: orderDetails.quantity,
              totalPrice: orderDetails.totalPrice,
              // Add other fields...
            }
          });
        },
        (error) => {
          console.error('Error fetching order details:', error);
        }
      );
    });

    this.myForm = this.fb.group({
      orderId: [null],  // Initialize orderId to null
      deliveryDate: [this.getCurrentDate()],
      status: [''],
      deliveryBoy: [null],
      order: this.fb.group({
        orderId: [null],
        orderStatus: [''],
        price: [null],
        quantity: [null],
        totalPrice: [null],
        // Add other fields...
      }),
    });
  }

  saveDelivery() {
    const formData = this.myForm.value;
    console.log(formData);
    // Include orderId in the payload
    formData.orderId = this.orderId;

    this.deliveryService.createDelivery(formData).subscribe(
      (data) => {
        this.deliverylist();
        console.log(data);
      },
      (error) => console.log(error)
    );
  }

  getCurrentDate(): string {
    const today = new Date();
    const isoDate = today.toISOString().split('T')[0];
    return isoDate;
  }

  saveDeliveryByOrder() {
    const formData = this.myForm.value;

    // Include orderId in the payload
    this.deliveryService.createDeiveryByOrder(this.delivery, this.orderId).subscribe(
      (data) => {
       
        console.log(data);
        // Assuming 'order' is available in the response, update the form control
        this.myForm.patchValue({
          order: data.order,
         
        });
      },
      (error) => console.log(error)
    );
  }

  deliverylist(){
    this.router.navigate(['/admin/deliverylist']);
  }

 
}
