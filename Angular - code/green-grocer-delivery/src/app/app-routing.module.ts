import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AboutusComponent } from './component/aboutus/aboutus.component';
import { AdminAddSupplierComponent } from './component/admin/admin-add-supplier/admin-add-supplier.component';
import { AdminAdddeliveryComponent } from './component/admin/admin-adddelivery/admin-adddelivery.component';
import { AdminAdddeliveryboyComponent } from './component/admin/admin-adddeliveryboy/admin-adddeliveryboy.component';
import { AdminAddproductComponent } from './component/admin/admin-addproduct/admin-addproduct.component';
import { AdminDeliveryboyComponent } from './component/admin/admin-deliveryboy/admin-deliveryboy.component';
import { AdminDeliverylistComponent } from './component/admin/admin-deliverylist/admin-deliverylist.component';
import { AdminHomeComponent } from './component/admin/admin-home/admin-home.component';
import { AdminListproductComponent } from './component/admin/admin-listproduct/admin-listproduct.component';
import { AdminOrderlistComponent } from './component/admin/admin-orderlist/admin-orderlist.component';
import { AdminSupplierComponent } from './component/admin/admin-supplier/admin-supplier.component';
import { AdminUpdateDeliveryBoyComponent } from './component/admin/admin-update-delivery-boy/admin-update-delivery-boy.component';
import { AdminUpdateProductComponent } from './component/admin/admin-update-product/admin-update-product.component';
import { AdminUpdateSupplierComponent } from './component/admin/admin-update-supplier/admin-update-supplier.component';
import { AppHeaderComponent } from './component/app-header/app-header.component';
import { ContactUsComponent } from './component/contact-us/contact-us.component';
import { CustomeOrderComponent } from './component/customer/custome-order/custome-order.component';
import { CustomeSignupComponent } from './component/customer/custome-signup/custome-signup.component';
import { CustomerCartComponent } from './component/customer/customer-cart/customer-cart.component';
import { CustomerHeaderComponent } from './component/customer/customer-header/customer-header.component';
import { CustomerHomeComponent } from './component/customer/customer-home/customer-home.component';
import { CustomerLoginComponent } from './component/customer/customer-login/customer-login.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { HomeComponent } from './component/home/home.component';

import { FooterComponent } from './footer/footer.component';

const routes: Routes = [
  //  {path:'',component:HomeComponent},{path:'user-login',component:UserLoginComponent},
  {path:'',component:HomeComponent},
  {path:'contactus',component:ContactUsComponent},
  {path:'footer',component:FooterComponent},
  {path:'aboutus',component:AboutusComponent},
  {path:'customer-login',component:CustomerLoginComponent},
  {path:'login',component:CustomerLoginComponent},
  {path:'signup',component:CustomeSignupComponent},
  {path:'forgate-password',component:ForgotPasswordComponent},
  {path:'admin',children:[
    {path:'home',component:AdminHomeComponent},
    {path:'delivery/:orderId',component:AdminAdddeliveryComponent},
    {path:'deliverylist',component:AdminDeliverylistComponent},
    {path:'deliveryboylist',component:AdminDeliveryboyComponent},
    {path:'adddeliveryboy',component:AdminAdddeliveryboyComponent},
    {path:'order',component:AdminOrderlistComponent},
    {path:'add-product',component:AdminAddproductComponent},
    {path:'product',component:AdminListproductComponent},
    {path:'update-product/:productId',component:AdminUpdateProductComponent},
    {path:'supplier',component:AdminSupplierComponent},
    {path:'update-supplier/:supplierId',component:AdminUpdateSupplierComponent},
    {path:'add-supplier',component:AdminAddSupplierComponent},
    {path:'update-deliveryboy/:deliveryBoyId',component:AdminUpdateDeliveryBoyComponent}

   
  ]},
  {path:'customer',children:[
   {path:'header',component:CustomerHeaderComponent},
   {path:'login',component:CustomerLoginComponent},
   {path:'cart',component:CustomerCartComponent},
   {path:'order',component:CustomeOrderComponent},
   {path:'home',component:CustomerHomeComponent},
   {path:'cartlist',component:CustomerCartComponent}
  ]}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
