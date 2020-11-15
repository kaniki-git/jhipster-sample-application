import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabUser } from 'app/shared/model/tab-user.model';
import { TabUserService } from './tab-user.service';

@Component({
  templateUrl: './tab-user-delete-dialog.component.html',
})
export class TabUserDeleteDialogComponent {
  tabUser?: ITabUser;

  constructor(protected tabUserService: TabUserService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabUserService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabUserListModification');
      this.activeModal.close();
    });
  }
}
