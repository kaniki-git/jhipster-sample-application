import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabExamphys } from 'app/shared/model/tab-examphys.model';
import { TabExamphysService } from './tab-examphys.service';

@Component({
  templateUrl: './tab-examphys-delete-dialog.component.html',
})
export class TabExamphysDeleteDialogComponent {
  tabExamphys?: ITabExamphys;

  constructor(
    protected tabExamphysService: TabExamphysService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabExamphysService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabExamphysListModification');
      this.activeModal.close();
    });
  }
}
