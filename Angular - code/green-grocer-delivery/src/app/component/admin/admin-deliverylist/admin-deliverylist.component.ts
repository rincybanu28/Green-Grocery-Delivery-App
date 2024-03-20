import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Delivery } from '../../../model/delivery';
import { DeliveryService } from '../../../services/delivery.service';

@Component({
  selector: 'app-admin-deliverylist',
  templateUrl: './admin-deliverylist.component.html',
  styleUrl: './admin-deliverylist.component.css'
})
export class AdminDeliverylistComponent implements OnInit {
  deliveryList: Delivery[] = [];
  // deliveryId:any;
  constructor(private deliveryService: DeliveryService,private router :Router,private route :ActivatedRoute) {}

  ngOnInit(): void {
    this.getDelivery();
  }
    public getDelivery(){
    this.deliveryService.getDeliveryList().subscribe(
      (response: any) => {
        console.log('Complete Server Response:', response);
        if (Array.isArray(response)) {
          this.deliveryList = response;
          console.log('Delivery List Length:', this.deliveryList.length);
        } else if (response && response.deliveryId) {
          this.deliveryList = [response];
          console.log('Delivery List Length:', this.deliveryList.length);
        } else {
          console.error('Invalid server response:', response);
        }
      },
      (error) => {
        console.error('Error fetching deliveries:', error);
      }
    );
  }

  deleteDelivery(deliveryId: any){
    this.deliveryService.deleteDelivery(deliveryId).subscribe( data => {
      // console.log(data);
      alert("Record Deleted");
       this.router.navigate(['/admin/order']);
    })
}
  
delivery(): void {
//  this.router.navigate(['/admin/order']);
this.getDelivery();
 }


}


