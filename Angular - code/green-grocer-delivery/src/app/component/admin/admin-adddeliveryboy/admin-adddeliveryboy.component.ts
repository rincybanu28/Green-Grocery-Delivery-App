import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DeliveryBoy } from '../../../model/delivery-boy';
import { DeliveryBoyService } from '../../../services/delivery-boy.service';

@Component({
  selector: 'app-admin-adddeliveryboy',
  templateUrl: './admin-adddeliveryboy.component.html',
  styleUrl: './admin-adddeliveryboy.component.css'
})
export class AdminAdddeliveryboyComponent {
   deleveryboy:DeliveryBoy=new DeliveryBoy();
   deliveryBoyForm!: FormGroup;
   @Output() deliveryBoyCreated = new EventEmitter<DeliveryBoy>();
   constructor(private deliveryBoyService:DeliveryBoyService, 
     private router:Router, private fb: FormBuilder) { 
       this.deliveryBoyForm = this.fb.group({
         deliveryBoyName: ['', [Validators.required]],
         
         deliveyBoyContact: ['', [Validators.required]],
         deliveryBoyEmail: ['', [Validators.required, Validators.email]],
         deliveryBoyAddress: ['', Validators.required],
         deliveryBoyCity: ['', Validators.required],
         deliveryBoyPassword: ['', Validators.required]
       });
     }
 
     // goToStudentList()
     // {
     //   this.router.navigate(['/deliveryboyes'])
     // }
   onSubmit():void
   {

    if (!this.deliveryBoyForm.valid) {
      alert('Fill the Form');
      return;
    }
      if(this.deliveryBoyForm.valid)
      {
         this.deliveryBoyService.addBoy(this.deliveryBoyForm.value).subscribe
         (
           (response)=>{
             console.log("Delivery Boy record is created successfully",response)
             alert("Delivery Boy added sucessfully");
             let ref = document.getElementById('cancel')
             ref?.click();
             this.deliveryBoyForm.reset();
             this.deliveryBoyCreated.emit();  //to reset form data after submiting
             this.goToDeliveryBoyList();
            
          // this.goToStudentList();
           },
           (error)=>{
             console.log("Error while creating Delivery Boy",error)
             alert("Failed to add record");
           }
         );
          
         // onReset():void{
         //   this.deliveryBoyForm.reset();
         // }
      }
   }
 
 
   goToDeliveryBoyList():void{
     this.router.navigate(['/admin/deliveryboylist']);
   }
 
  
 
 
}
