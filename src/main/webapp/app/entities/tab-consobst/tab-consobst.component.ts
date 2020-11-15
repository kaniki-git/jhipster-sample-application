import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabConsobst } from 'app/shared/model/tab-consobst.model';
import { TabConsobstService } from './tab-consobst.service';
import { TabConsobstDeleteDialogComponent } from './tab-consobst-delete-dialog.component';

@Component({
  selector: 'jhi-tab-consobst',
  templateUrl: './tab-consobst.component.html',
})
export class TabConsobstComponent implements OnInit, OnDestroy {
  tabConsobsts?: ITabConsobst[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabConsobstService: TabConsobstService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabConsobstService.query().subscribe((res: HttpResponse<ITabConsobst[]>) => (this.tabConsobsts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabConsobsts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabConsobst): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabConsobsts(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabConsobstListModification', () => this.loadAll());
  }

  delete(tabConsobst: ITabConsobst): void {
    const modalRef = this.modalService.open(TabConsobstDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabConsobst = tabConsobst;
  }
}
