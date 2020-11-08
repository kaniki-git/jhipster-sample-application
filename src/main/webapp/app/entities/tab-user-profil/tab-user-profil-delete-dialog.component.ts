import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabUserProfil } from 'app/shared/model/tab-user-profil.model';
import { TabUserProfilService } from './tab-user-profil.service';

@Component({
  templateUrl: './tab-user-profil-delete-dialog.component.html',
})
export class TabUserProfilDeleteDialogComponent {
  tabUserProfil?: ITabUserProfil;

  constructor(
    protected tabUserProfilService: TabUserProfilService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabUserProfilService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabUserProfilListModification');
      this.activeModal.close();
    });
  }
}
