import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { TabUrgenceService } from './tab-urgence.service';

@Component({
  templateUrl: './tab-urgence-delete-dialog.component.html',
})
export class TabUrgenceDeleteDialogComponent {
  tabUrgence?: ITabUrgence;

  constructor(
    protected tabUrgenceService: TabUrgenceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabUrgenceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabUrgenceListModification');
      this.activeModal.close();
    });
  }
}
