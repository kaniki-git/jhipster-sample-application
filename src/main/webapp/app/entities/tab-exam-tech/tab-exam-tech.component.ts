import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabExamTech } from 'app/shared/model/tab-exam-tech.model';
import { TabExamTechService } from './tab-exam-tech.service';
import { TabExamTechDeleteDialogComponent } from './tab-exam-tech-delete-dialog.component';

@Component({
  selector: 'jhi-tab-exam-tech',
  templateUrl: './tab-exam-tech.component.html',
})
export class TabExamTechComponent implements OnInit, OnDestroy {
  tabExamTeches?: ITabExamTech[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabExamTechService: TabExamTechService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabExamTechService.query().subscribe((res: HttpResponse<ITabExamTech[]>) => (this.tabExamTeches = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabExamTeches();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabExamTech): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabExamTeches(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabExamTechListModification', () => this.loadAll());
  }

  delete(tabExamTech: ITabExamTech): void {
    const modalRef = this.modalService.open(TabExamTechDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabExamTech = tabExamTech;
  }
}
