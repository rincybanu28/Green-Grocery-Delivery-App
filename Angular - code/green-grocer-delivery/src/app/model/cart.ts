import { Product } from "./product";

export interface Cart {

    cartId : number ;
    mrpPrice : number;
    quantity : number ;
    customer : any;
    product: Product;
}
