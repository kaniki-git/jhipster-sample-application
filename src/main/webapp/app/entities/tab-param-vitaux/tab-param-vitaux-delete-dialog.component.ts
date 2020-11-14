import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { TabParamVitauxService } from './tab-param-vitaux.service';

@Component({
  templateUrl: './tab-param-vitaux-delete-dialog.component.html',
})
export class TabParamVitauxDeleteDialogComponent {
  tabParamVitaux?: ITabParamVitaux;

  constructor(
    protected tabParamVitauxService: TabParamVitauxService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabParamVitauxService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabParamVitauxListModification');
      this.activeModal.close();
    });
  }
}
