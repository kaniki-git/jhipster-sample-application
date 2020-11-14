import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabProfil } from 'app/shared/model/tab-profil.model';
import { TabProfilService } from './tab-profil.service';

@Component({
  templateUrl: './tab-profil-delete-dialog.component.html',
})
export class TabProfilDeleteDialogComponent {
  tabProfil?: ITabProfil;

  constructor(protected tabProfilService: TabProfilService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabProfilService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabProfilListModification');
      this.activeModal.close();
    });
  }
}
