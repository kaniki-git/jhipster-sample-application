import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';
import { TabHistoriquePatientService } from './tab-historique-patient.service';

@Component({
  templateUrl: './tab-historique-patient-delete-dialog.component.html',
})
export class TabHistoriquePatientDeleteDialogComponent {
  tabHistoriquePatient?: ITabHistoriquePatient;

  constructor(
    protected tabHistoriquePatientService: TabHistoriquePatientService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabHistoriquePatientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabHistoriquePatientListModification');
      this.activeModal.close();
    });
  }
}
