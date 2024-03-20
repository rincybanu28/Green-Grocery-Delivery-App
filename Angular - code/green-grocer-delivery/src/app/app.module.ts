import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule } from '@angular/material/icon';
import { DatePipe } from '@angular/common';
import {MatMenuModule} from '@angular/material/menu';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './component/home/home.component';
import { ContactUsComponent } from './component/contact-us/contact-us.component';
import { AboutusComponent } from './component/aboutus/aboutus.component';
import { AppHeaderComponent } from './component/app-header/app-header.component';
import { CustomerLoginComponent } from './component/customer/customer-login/customer-login.component';
import { AdminHeaderComponent } from './component/admin/admin-header/admin-header.component';
import { AdminHomeComponent } from './component/admin/admin-home/admin-home.component';
import { AdminAddproductComponent } from './component/admin/admin-addproduct/admin-addproduct.component';
import { AdminListproductComponent } from './component/admin/admin-listproduct/admin-listproduct.component';
import { AdminAdddeliveryComponent } from './component/admin/admin-adddelivery/admin-adddelivery.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminOrderlistComponent } from './component/admin/admin-orderlist/admin-orderlist.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminAdddeliveryboyComponent } from './component/admin/admin-adddeliveryboy/admin-adddeliveryboy.component';
import { AdminDeliverylistComponent } from './component/admin/admin-deliverylist/admin-deliverylist.component';
import { CustomerHeaderComponent } from './component/customer/customer-header/customer-header.component';
import { CustomerHomeComponent } from './component/customer/customer-home/customer-home.component';
import { CustomerCartComponent } from './component/customer/customer-cart/customer-cart.component';
import { CustomeSignupComponent } from './component/customer/custome-signup/custome-signup.component';
import { CustomeOrderComponent } from './component/customer/custome-order/custome-order.component';
import { CustomeOrderHistoryComponent } from './component/customer/custome-order-history/custome-order-history.component';
import { PagingComponent } from './component/paging/paging.component';
import { AdminSupplierComponent } from './component/admin/admin-supplier/admin-supplier.component';
import { AdminAddSupplierComponent } from './component/admin/admin-add-supplier/admin-add-supplier.component';
import { AdminUpdateSupplierComponent } from './component/admin/admin-update-supplier/admin-update-supplier.component';
import { CommonModule } from '@angular/common';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AdminUpdateDeliveryBoyComponent } from './component/admin/admin-update-delivery-boy/admin-update-delivery-boy.component';
import { AdminDeliveryboyComponent } from './component/admin/admin-deliveryboy/admin-deliveryboy.component';
import { AdminUpdateProductComponent } from './component/admin/admin-update-product/admin-update-product.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';

@NgModule({
  declarations: [
    AppComponent,
    
    FooterComponent,
    HomeComponent,
    ContactUsComponent,
    AboutusComponent,
    AppHeaderComponent,
   CustomerLoginComponent,
   AdminHeaderComponent,
   AdminHomeComponent,
   AdminAddproductComponent,
   AdminListproductComponent,
   AdminAdddeliveryComponent,
   AdminOrderlistComponent,
   AdminAdddeliveryboyComponent,
   AdminDeliverylistComponent,
   CustomerHeaderComponent,
   CustomerHomeComponent,
   CustomerCartComponent,
   CustomeSignupComponent,
   CustomeOrderComponent,
   CustomeOrderHistoryComponent,
   PagingComponent,
   AdminSupplierComponent,
   AdminAddSupplierComponent,
   AdminUpdateSupplierComponent,
   AdminUpdateDeliveryBoyComponent,
   AdminDeliveryboyComponent,
   AdminUpdateProductComponent,
   ForgotPasswordComponent
  

  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ModalModule.forRoot(),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatMenuModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatRippleModule,
    MatNativeDateModule,
    MatInputModule,
    MatDialogModule,
    MatIconModule,
    MatButtonToggleModule,
    CommonModule,
    MatDialogModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
