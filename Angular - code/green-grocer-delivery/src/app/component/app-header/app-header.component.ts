import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-app-header',
  templateUrl: './app-header.component.html',
  styleUrl: './app-header.component.css'
})
export class AppHeaderComponent implements OnInit {
url:string='/';
constructor(private route:Router){}
ngOnInit(): void {
  this.route.events.pipe(
    filter(event => event instanceof NavigationStart)
  ).subscribe((event: any) => {
    this.url = event?.url;
  });

}  

gotourl(url: any): void {
  this.route.navigate(["/"+url]);
}
}




