import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabSpecialite } from 'app/shared/model/tab-specialite.model';
import { TabSpecialiteService } from './tab-specialite.service';

@Component({
  templateUrl: './tab-specialite-delete-dialog.component.html',
})
export class TabSpecialiteDeleteDialogComponent {
  tabSpecialite?: ITabSpecialite;

  constructor(
    protected tabSpecialiteService: TabSpecialiteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabSpecialiteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabSpecialiteListModification');
      this.activeModal.close();
    });
  }
}
