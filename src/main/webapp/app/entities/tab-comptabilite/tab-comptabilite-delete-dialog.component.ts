import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabComptabilite } from 'app/shared/model/tab-comptabilite.model';
import { TabComptabiliteService } from './tab-comptabilite.service';

@Component({
  templateUrl: './tab-comptabilite-delete-dialog.component.html',
})
export class TabComptabiliteDeleteDialogComponent {
  tabComptabilite?: ITabComptabilite;

  constructor(
    protected tabComptabiliteService: TabComptabiliteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabComptabiliteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabComptabiliteListModification');
      this.activeModal.close();
    });
  }
}
