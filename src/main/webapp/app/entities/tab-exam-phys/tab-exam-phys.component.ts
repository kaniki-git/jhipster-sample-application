import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabExamPhys } from 'app/shared/model/tab-exam-phys.model';
import { TabExamPhysService } from './tab-exam-phys.service';
import { TabExamPhysDeleteDialogComponent } from './tab-exam-phys-delete-dialog.component';

@Component({
  selector: 'jhi-tab-exam-phys',
  templateUrl: './tab-exam-phys.component.html',
})
export class TabExamPhysComponent implements OnInit, OnDestroy {
  tabExamPhys?: ITabExamPhys[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabExamPhysService: TabExamPhysService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabExamPhysService.query().subscribe((res: HttpResponse<ITabExamPhys[]>) => (this.tabExamPhys = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabExamPhys();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabExamPhys): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabExamPhys(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabExamPhysListModification', () => this.loadAll());
  }

  delete(tabExamPhys: ITabExamPhys): void {
    const modalRef = this.modalService.open(TabExamPhysDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabExamPhys = tabExamPhys;
  }
}
