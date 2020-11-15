import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabRefFonction } from 'app/shared/model/tab-ref-fonction.model';
import { TabRefFonctionService } from './tab-ref-fonction.service';

@Component({
  templateUrl: './tab-ref-fonction-delete-dialog.component.html',
})
export class TabRefFonctionDeleteDialogComponent {
  tabRefFonction?: ITabRefFonction;

  constructor(
    protected tabRefFonctionService: TabRefFonctionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabRefFonctionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabRefFonctionListModification');
      this.activeModal.close();
    });
  }
}
