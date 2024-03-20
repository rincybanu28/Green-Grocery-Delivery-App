import { Supplier } from "./supplier";

export class Product {
    productId!: number;
    productName!: string;
    productQuantity!: number;
    productImage!: string;
    buyDate!: Date;
    price!: number;
    category!: string;
    supplier!: Supplier;

}
