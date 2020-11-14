import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from './tab-consultation.service';

@Component({
  templateUrl: './tab-consultation-delete-dialog.component.html',
})
export class TabConsultationDeleteDialogComponent {
  tabConsultation?: ITabConsultation;

  constructor(
    protected tabConsultationService: TabConsultationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabConsultationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabConsultationListModification');
      this.activeModal.close();
    });
  }
}
