import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from './tab-patient.service';

@Component({
  templateUrl: './tab-patient-delete-dialog.component.html',
})
export class TabPatientDeleteDialogComponent {
  tabPatient?: ITabPatient;

  constructor(
    protected tabPatientService: TabPatientService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabPatientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabPatientListModification');
      this.activeModal.close();
    });
  }
}
