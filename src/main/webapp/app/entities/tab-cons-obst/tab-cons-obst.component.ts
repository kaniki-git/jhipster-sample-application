import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabConsObst } from 'app/shared/model/tab-cons-obst.model';
import { TabConsObstService } from './tab-cons-obst.service';
import { TabConsObstDeleteDialogComponent } from './tab-cons-obst-delete-dialog.component';

@Component({
  selector: 'jhi-tab-cons-obst',
  templateUrl: './tab-cons-obst.component.html',
})
export class TabConsObstComponent implements OnInit, OnDestroy {
  tabConsObsts?: ITabConsObst[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabConsObstService: TabConsObstService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabConsObstService.query().subscribe((res: HttpResponse<ITabConsObst[]>) => (this.tabConsObsts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabConsObsts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabConsObst): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabConsObsts(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabConsObstListModification', () => this.loadAll());
  }

  delete(tabConsObst: ITabConsObst): void {
    const modalRef = this.modalService.open(TabConsObstDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabConsObst = tabConsObst;
  }
}
