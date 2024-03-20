import { Customer } from "./customer";
import { Product } from "./product";

export class Order {
    // orderId: number | undefined;
    // orderStatus: string | undefined;
    // price: number | undefined;
    // quantity: number | undefined;
    // totalPrice: number | undefined;
    // paymentStatus: string | undefined;
    // orderedDate: Date | undefined;
    // productName: string | undefined;
    // customer: any | undefined;
    // productDescription: string | undefined;
    // products:Array<Product> | undefined;



    orderId:number | undefined;

	orderedDate:Date | undefined;

	orderStatus:string | undefined;

	mrpPrice:any;
	
	totalPrice:any;

	quantity:any;

	totalQuantity:any;

	paymentStatus:string | undefined;
	
	productName:string | undefined;
	
	productImage:string | undefined;
    
    product:Array<Product> | undefined;
    customer:Customer |undefined;
   
}
