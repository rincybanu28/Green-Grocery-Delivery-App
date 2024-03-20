import { ChangeDetectorRef, Component } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css'
})
export class AdminHeaderComponent {

  url: string = '';
  customerName: string = '';

constructor(
  private route:Router,
  private customerService:CustomerService,
  private changeDetector:ChangeDetectorRef
  ){
    if (this.customerService.getCustomerName() !== null) {
      this.customerName = this.customerService.getCustomerName();
    }
  }

  ngOnInit(): void {
    this.route.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });

  }  
  gotourl(link: string): void {
    if (link === '/admin/logout') {
      this.customerService.adminLogout();
      return;
    }
    this.route.navigate([link]);

}
}
