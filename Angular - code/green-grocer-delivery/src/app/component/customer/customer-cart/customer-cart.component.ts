import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
// import * as _ from 'lodash';
import { Router } from '@angular/router';
import { forkJoin, take } from 'rxjs';
import { Cart } from '../../../model/cart';
import { CartsService } from '../../../services/carts.service';
import { CustomerService } from '../../../services/customer.service';
import * as _ from 'lodash';
import { Product } from '../../../model/product';

@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css'],
  providers: [DatePipe]
})
// implements OnInit
export class CustomerCartComponent  implements OnInit{
  // [x: string]: any;
  cartList: Cart[] = [];
  cartListBackup: Cart[] = [];
  grandTotal: number = 0;
  customer: any = {};
 


  constructor(
    private cartService: CartsService,
    private customerService:CustomerService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.customerService.isCustomerLoginPresent();
    this.getCartList();
    this.getCustomerDetail();
  }


  ngOnInit(): void {
  }

  getCartList(): void {
    this.cartService.cartList().pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Cart API Response:", res);

        if (!!res && Array.isArray(res)) {
          const customerId = parseInt(this.customerService.getCustomerAuthorization(), 10);

          // Filter cart list based on customer ID
          const customerFilter = res.filter((item: Cart) => item?.customer?.customerId === customerId);
          console.log("Customer filter:", customerFilter);

          this.cartList = customerFilter;
          this.cartListBackup = _.cloneDeep(customerFilter);

          if (this.cartList.length > 0) {
            // Calculate grand total
            this.cartList.forEach((item: Cart) => {
              this.grandTotal += (item?.mrpPrice * item?.quantity);
            });
          }
        }
      },
      (err) => {
        console.error("Error fetching cart list:", err);
      }
    );
  }


  getCurrentDate(): string {
    const today = new Date();
    const isoDate = today.toISOString().split('T')[0];
    return isoDate;
  }
  
  getTotal(quantity: number = 0, mrpPrice: number = 0): number {
    return quantity * mrpPrice;
  }


  
  placeOrder(): void {
    let totalPrice: number = 0;
    let mrpPrice: number = 0;
    let quantity: number = 0;
    let cartId: number = 0;
    let productImage: string = '';
    let productName: string = '';
    const deleteCartReq: any[] = [];
    const productItems: Product[] = [];
    this.cartList.forEach((item: Cart) => {
        productItems.push(item.product);
        totalPrice += (item.product.price * item.quantity);
        deleteCartReq.push(this.cartService.deleteCart(item.cartId));
         mrpPrice=item.mrpPrice;
         quantity=item.quantity;
         cartId=item.cartId;
          productImage=item.product?.productImage;
          productName=item.product?.productName;
    });
    console.log('>>>>>>>>', totalPrice)
    console.log('>>>>>>>>', productItems)
    const body: any = {
        totalPrice: totalPrice,  
        orderStatus: "success",
        paymentStatus: "success",
        orderedDate: this.getCurrentDate(),
        customer: this.customer,
        product: productItems,
        mrpPrice:mrpPrice,
        quantity:quantity,
        cartId:cartId,
        productImage:productImage,
        productName:productName
    };
    console.log('before place:', body);
    this.cartService.placeOrderItem(this.customer?.customerId, body).pipe(take(1)).subscribe(
        (res: any) => {
            console.log('Place Order Response:', res);
            forkJoin(deleteCartReq).pipe(take(1)).subscribe();
            alert("Place order Successfully");
            this.router.navigate(["/customer/order"]);
        },
        (error) => {
            console.error('Error placing order:', error);
            // Handle the error, display a user-friendly message, or log it for further investigation.
        }
    );
}




getCustomerDetail(): void {
  const cid = this.customerService.getCustomerAuthorization();
  console.log(cid);
  this.customerService.getCustomerById(cid).pipe(take(1)).subscribe(
    (res: any) => {
      console.log("Customer Details***", res);
      if (!!res && res?.customerId) {
        this.customer = res;
      }
    }, err => {
      console.log("Err");
    }
  )
}

deleteCart(cart:Cart, showAlert: boolean = true):void{
  this.cartService.deleteCart(cart?.cartId).pipe(take(1)).subscribe(
    (res: any) => {
      if (showAlert) {
        alert("Product deleted sucessfully");
      }
     
      this.getCartList();
    }, err => {
      console.log("Err");
    }
  )
}

onIncreaseQunatity(cart: Cart): void {
  const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
  // const bac = Object.assign(this.cartListBackup);
  const indexBackup = this.cartListBackup.findIndex((item: Cart) => item.cartId === cart?.cartId);
  const qty = cart.quantity + 1;
  console.log( this.cartListBackup[indexBackup].quantity , '>>>>>>' , (cart.product?.productQuantity ))
  if (qty > (cart.product?.productQuantity  + this.cartListBackup[indexBackup].quantity) ) {
    alert('Added quantity should not greater than avaiable quantity');
    return;
  }
  this.cartList[index].quantity = qty;
  this.updateGrantTotal();
}

onDecreaseQunatity(cart: Cart): void {
  const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
  const qty = cart.quantity - 1;
  if (qty === 0) {
    this.deleteCart(cart, false);
  }
  this.cartList[index].quantity = qty;
  this.updateGrantTotal();
}

updateGrantTotal(): void {
  let total = 0;
  this.cartList.map((item: Cart) => {
    total+= (item?.mrpPrice * item?.quantity);
   
  })
  this.grandTotal = total;
}




}
