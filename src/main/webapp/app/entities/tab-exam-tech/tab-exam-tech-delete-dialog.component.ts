import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITabExamTech } from 'app/shared/model/tab-exam-tech.model';
import { TabExamTechService } from './tab-exam-tech.service';

@Component({
  templateUrl: './tab-exam-tech-delete-dialog.component.html',
})
export class TabExamTechDeleteDialogComponent {
  tabExamTech?: ITabExamTech;

  constructor(
    protected tabExamTechService: TabExamTechService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tabExamTechService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tabExamTechListModification');
      this.activeModal.close();
    });
  }
}
