import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabConsObst } from 'app/shared/model/tab-cons-obst.model';
import { TabConsObstService } from './tab-cons-obst.service';

@Component({
  templateUrl: './tab-cons-obst-delete-dialog.component.html',
})
export class TabConsObstDeleteDialogComponent {
  tabConsObst?: ITabConsObst;

  constructor(
    protected tabConsObstService: TabConsObstService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabConsObstService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabConsObstListModification');
      this.activeModal.close();
    });
  }
}
