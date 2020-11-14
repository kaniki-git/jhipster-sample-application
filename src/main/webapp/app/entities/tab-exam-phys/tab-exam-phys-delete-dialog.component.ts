import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabExamPhys } from 'app/shared/model/tab-exam-phys.model';
import { TabExamPhysService } from './tab-exam-phys.service';

@Component({
  templateUrl: './tab-exam-phys-delete-dialog.component.html',
})
export class TabExamPhysDeleteDialogComponent {
  tabExamPhys?: ITabExamPhys;

  constructor(
    protected tabExamPhysService: TabExamPhysService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabExamPhysService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabExamPhysListModification');
      this.activeModal.close();
    });
  }
}
