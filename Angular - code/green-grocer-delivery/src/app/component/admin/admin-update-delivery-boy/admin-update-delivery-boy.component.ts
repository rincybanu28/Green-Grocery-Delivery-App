import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DeliveryBoy } from '../../../model/delivery-boy';
import { DeliveryBoyService } from '../../../services/delivery-boy.service';

@Component({
  selector: 'app-admin-update-delivery-boy',
  templateUrl: './admin-update-delivery-boy.component.html',
  styleUrl: './admin-update-delivery-boy.component.css'
})
export class AdminUpdateDeliveryBoyComponent implements OnInit{
  deliveryboy: DeliveryBoy = new DeliveryBoy;
  
  deliveryBoyId:any;

  constructor(private deliveryBoyService:DeliveryBoyService, private router : Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.deliveryBoyId = this.route.snapshot.params[ 'deliveryBoyId' ];
    this.deliveryBoyService.getDeliveryBoyById(this.deliveryBoyId).subscribe((data: DeliveryBoy) =>{
      this.deliveryboy = data;
      // console.log('Supplier details:', this.deliveryBoy);
    },(error: any) => console.log(error));
  }
//   

  // onSubmit(){

  //   this.deliveryBoyService.updateDeliveryBoy(this.deliveryBoyId, this.deliveryboy).subscribe( data =>{
  //     //this.updateSupplierModal.nativeElement.modal('hide');
  //     this.deliveryboy=data;
  //     this.goToDeliveryBoyList();
  //   },error => console.log(error));
  // }





//  
onUpdateClick(){
  this.deliveryBoyService.updateDeliveryBoy(this.deliveryBoyId, this.deliveryboy).subscribe( data =>{
    //this.updateSupplierModal.nativeElement.modal('hide');
    this.deliveryboy=data;
    this.goToDeliveryBoyList();
  },error => console.log(error));
  //console.log('Update button clicked');
  alert('Update Delivery Boy Records Successfully');

}

    
goToDeliveryBoyList():void{
      this.router.navigate(['/admin/deliveryboylist']);
    }

    close():void{
      this.router.navigate(['/admin/deliveryboylist']);
    }
}
