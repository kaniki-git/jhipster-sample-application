import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';
import { TabAdministratifService } from './tab-administratif.service';

@Component({
  templateUrl: './tab-administratif-delete-dialog.component.html',
})
export class TabAdministratifDeleteDialogComponent {
  tabAdministratif?: ITabAdministratif;

  constructor(
    protected tabAdministratifService: TabAdministratifService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabAdministratifService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabAdministratifListModification');
      this.activeModal.close();
    });
  }
}
