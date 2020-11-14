import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from './tab-personnel.service';

@Component({
  templateUrl: './tab-personnel-delete-dialog.component.html',
})
export class TabPersonnelDeleteDialogComponent {
  tabPersonnel?: ITabPersonnel;

  constructor(
    protected tabPersonnelService: TabPersonnelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabPersonnelService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabPersonnelListModification');
      this.activeModal.close();
    });
  }
}
