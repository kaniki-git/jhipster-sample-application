import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from './tab-hospital.service';

@Component({
  templateUrl: './tab-hospital-delete-dialog.component.html',
})
export class TabHospitalDeleteDialogComponent {
  tabHospital?: ITabHospital;

  constructor(
    protected tabHospitalService: TabHospitalService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabHospitalService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabHospitalListModification');
      this.activeModal.close();
    });
  }
}
