import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabBiologie } from 'app/shared/model/tab-biologie.model';
import { TabBiologieService } from './tab-biologie.service';

@Component({
  templateUrl: './tab-biologie-delete-dialog.component.html',
})
export class TabBiologieDeleteDialogComponent {
  tabBiologie?: ITabBiologie;

  constructor(
    protected tabBiologieService: TabBiologieService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabBiologieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabBiologieListModification');
      this.activeModal.close();
    });
  }
}
