import { DeliveryBoy } from "./delivery-boy";
import { Order } from "./order";

export class Delivery {
    deliveryId : number | undefined;
    deliveryDate: string | undefined;
    status: string | undefined;
    deliveryBoy:DeliveryBoy | undefined;
    order:Order | undefined
}
