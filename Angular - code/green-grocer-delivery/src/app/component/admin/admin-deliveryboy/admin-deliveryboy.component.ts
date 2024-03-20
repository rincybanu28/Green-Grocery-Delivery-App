import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DeliveryBoy } from '../../../model/delivery-boy';

import { DeliveryBoyService } from '../../../services/delivery-boy.service';

@Component({
  selector: 'app-admin-deliveryboy',
  templateUrl: './admin-deliveryboy.component.html',
  styleUrl: './admin-deliveryboy.component.css'
})
export class AdminDeliveryboyComponent {


  @ViewChild('addDeliveryBoyModal') addDeliveryBoyModal!: ElementRef;
 
  

  openModal() {
    // Accessing the modal using ViewChild and ElementRef
    this.addDeliveryBoyModal.nativeElement.modal('show');
    
     }

     reloadPage(): void {
      window.location.reload();
    }

     deliveryboy: DeliveryBoy = new DeliveryBoy();

     deliveryboys: DeliveryBoy[] | undefined;

   

    constructor(private deliveryBoyService:DeliveryBoyService,
      private router: Router){}
    ngOnInit():void  //this method is call whenever page is loaded
    {
      
       let resp=this.deliveryBoyService.getAllDeliveryBoys();
      let uData= resp.subscribe((data)=>this.deliveryboys=data);
       console.log('resp',uData);
  
    }

    updateDeliveryBoy(deliveryBoyId: any): void {
      if (this.router) {
          this.router.navigate(['/admin/update-deliveryboy', deliveryBoyId]);
      } else {
          console.error('Router is not initialized.');
      }
  }





    deleteDeliveryBoy(deliveryBoyId: any){
      this.deliveryBoyService.deleteDeliveryBoy(deliveryBoyId).subscribe( data => {
        console.log(data);
        alert("Record Deleted sucessfully");
        //  this.deliveryBoyService.getDeliveryBoyList();
        this.goToDeliveryBoyList();
         
      })
  }

  goToDeliveryBoyList():void{
    this.router.navigate(['/admin/deliveryboy']);
  }


  
}
