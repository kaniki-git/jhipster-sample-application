import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';
import { TabParamvitauxService } from './tab-paramvitaux.service';

@Component({
  templateUrl: './tab-paramvitaux-delete-dialog.component.html',
})
export class TabParamvitauxDeleteDialogComponent {
  tabParamvitaux?: ITabParamvitaux;

  constructor(
    protected tabParamvitauxService: TabParamvitauxService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabParamvitauxService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabParamvitauxListModification');
      this.activeModal.close();
    });
  }
}
