import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabRefPays } from 'app/shared/model/tab-ref-pays.model';
import { TabRefPaysService } from './tab-ref-pays.service';
import { TabRefPaysDeleteDialogComponent } from './tab-ref-pays-delete-dialog.component';

@Component({
  selector: 'jhi-tab-ref-pays',
  templateUrl: './tab-ref-pays.component.html',
})
export class TabRefPaysComponent implements OnInit, OnDestroy {
  tabRefPays?: ITabRefPays[];
  eventSubscriber?: Subscription;

  constructor(protected tabRefPaysService: TabRefPaysService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tabRefPaysService.query().subscribe((res: HttpResponse<ITabRefPays[]>) => (this.tabRefPays = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabRefPays();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabRefPays): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabRefPays(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabRefPaysListModification', () => this.loadAll());
  }

  delete(tabRefPays: ITabRefPays): void {
    const modalRef = this.modalService.open(TabRefPaysDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabRefPays = tabRefPays;
  }
}
