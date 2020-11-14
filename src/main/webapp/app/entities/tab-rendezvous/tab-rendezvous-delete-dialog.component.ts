import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabRendezvous } from 'app/shared/model/tab-rendezvous.model';
import { TabRendezvousService } from './tab-rendezvous.service';

@Component({
  templateUrl: './tab-rendezvous-delete-dialog.component.html',
})
export class TabRendezvousDeleteDialogComponent {
  tabRendezvous?: ITabRendezvous;

  constructor(
    protected tabRendezvousService: TabRendezvousService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabRendezvousService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabRendezvousListModification');
      this.activeModal.close();
    });
  }
}
