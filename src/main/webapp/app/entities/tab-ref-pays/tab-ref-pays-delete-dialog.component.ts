import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabRefPays } from 'app/shared/model/tab-ref-pays.model';
import { TabRefPaysService } from './tab-ref-pays.service';

@Component({
  templateUrl: './tab-ref-pays-delete-dialog.component.html',
})
export class TabRefPaysDeleteDialogComponent {
  tabRefPays?: ITabRefPays;

  constructor(
    protected tabRefPaysService: TabRefPaysService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabRefPaysService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabRefPaysListModification');
      this.activeModal.close();
    });
  }
}
