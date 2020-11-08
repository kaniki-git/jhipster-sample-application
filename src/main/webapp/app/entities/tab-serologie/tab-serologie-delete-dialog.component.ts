import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabSerologie } from 'app/shared/model/tab-serologie.model';
import { TabSerologieService } from './tab-serologie.service';

@Component({
  templateUrl: './tab-serologie-delete-dialog.component.html',
})
export class TabSerologieDeleteDialogComponent {
  tabSerologie?: ITabSerologie;

  constructor(
    protected tabSerologieService: TabSerologieService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabSerologieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabSerologieListModification');
      this.activeModal.close();
    });
  }
}
