import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabGrossesse } from 'app/shared/model/tab-grossesse.model';
import { TabGrossesseService } from './tab-grossesse.service';

@Component({
  templateUrl: './tab-grossesse-delete-dialog.component.html',
})
export class TabGrossesseDeleteDialogComponent {
  tabGrossesse?: ITabGrossesse;

  constructor(
    protected tabGrossesseService: TabGrossesseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabGrossesseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabGrossesseListModification');
      this.activeModal.close();
    });
  }
}
