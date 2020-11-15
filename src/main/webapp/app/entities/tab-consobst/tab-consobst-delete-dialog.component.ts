import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabConsobst } from 'app/shared/model/tab-consobst.model';
import { TabConsobstService } from './tab-consobst.service';

@Component({
  templateUrl: './tab-consobst-delete-dialog.component.html',
})
export class TabConsobstDeleteDialogComponent {
  tabConsobst?: ITabConsobst;

  constructor(
    protected tabConsobstService: TabConsobstService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabConsobstService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabConsobstListModification');
      this.activeModal.close();
    });
  }
}
