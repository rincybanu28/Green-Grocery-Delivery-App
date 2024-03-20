import { ElementRef, Injectable } from '@angular/core';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
@Injectable({
  providedIn: 'root'
})
export class ModelServiceService {
  bsModalRef: BsModalRef | undefined;

  constructor(private modalService: BsModalService) {}

  openModal(template: any): void {
    this.bsModalRef = this.modalService.show(template, { class: 'modal-lg' });
  }

  closeModal(): void {
    if (this.bsModalRef) {
      this.bsModalRef.hide();
    }
  }
}
