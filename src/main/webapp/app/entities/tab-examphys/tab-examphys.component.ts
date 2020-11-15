import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabExamphys } from 'app/shared/model/tab-examphys.model';
import { TabExamphysService } from './tab-examphys.service';
import { TabExamphysDeleteDialogComponent } from './tab-examphys-delete-dialog.component';

@Component({
  selector: 'jhi-tab-examphys',
  templateUrl: './tab-examphys.component.html',
})
export class TabExamphysComponent implements OnInit, OnDestroy {
  tabExamphys?: ITabExamphys[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabExamphysService: TabExamphysService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabExamphysService.query().subscribe((res: HttpResponse<ITabExamphys[]>) => (this.tabExamphys = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabExamphys();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabExamphys): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabExamphys(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabExamphysListModification', () => this.loadAll());
  }

  delete(tabExamphys: ITabExamphys): void {
    const modalRef = this.modalService.open(TabExamphysDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabExamphys = tabExamphys;
  }
}
