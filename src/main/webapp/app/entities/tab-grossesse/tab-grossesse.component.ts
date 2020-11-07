import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabGrossesse } from 'app/shared/model/tab-grossesse.model';
import { TabGrossesseService } from './tab-grossesse.service';
import { TabGrossesseDeleteDialogComponent } from './tab-grossesse-delete-dialog.component';

@Component({
  selector: 'jhi-tab-grossesse',
  templateUrl: './tab-grossesse.component.html',
})
export class TabGrossesseComponent implements OnInit, OnDestroy {
  tabGrossesses?: ITabGrossesse[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabGrossesseService: TabGrossesseService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabGrossesseService.query().subscribe((res: HttpResponse<ITabGrossesse[]>) => (this.tabGrossesses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabGrossesses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabGrossesse): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabGrossesses(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabGrossesseListModification', () => this.loadAll());
  }

  delete(tabGrossesse: ITabGrossesse): void {
    const modalRef = this.modalService.open(TabGrossesseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabGrossesse = tabGrossesse;
  }
}
