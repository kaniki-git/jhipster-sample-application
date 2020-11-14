import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { TabCoordonneeService } from './tab-coordonnee.service';

@Component({
  templateUrl: './tab-coordonnee-delete-dialog.component.html',
})
export class TabCoordonneeDeleteDialogComponent {
  tabCoordonnee?: ITabCoordonnee;

  constructor(
    protected tabCoordonneeService: TabCoordonneeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabCoordonneeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabCoordonneeListModification');
      this.activeModal.close();
    });
  }
}
