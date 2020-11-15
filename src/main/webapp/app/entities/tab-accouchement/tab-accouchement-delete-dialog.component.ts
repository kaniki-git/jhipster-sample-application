import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';
import { TabAccouchementService } from './tab-accouchement.service';

@Component({
  templateUrl: './tab-accouchement-delete-dialog.component.html',
})
export class TabAccouchementDeleteDialogComponent {
  tabAccouchement?: ITabAccouchement;

  constructor(
    protected tabAccouchementService: TabAccouchementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabAccouchementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabAccouchementListModification');
      this.activeModal.close();
    });
  }
}
