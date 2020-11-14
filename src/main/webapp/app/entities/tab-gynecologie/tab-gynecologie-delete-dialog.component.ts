import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from './tab-gynecologie.service';

@Component({
  templateUrl: './tab-gynecologie-delete-dialog.component.html',
})
export class TabGynecologieDeleteDialogComponent {
  tabGynecologie?: ITabGynecologie;

  constructor(
    protected tabGynecologieService: TabGynecologieService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabGynecologieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabGynecologieListModification');
      this.activeModal.close();
    });
  }
}
