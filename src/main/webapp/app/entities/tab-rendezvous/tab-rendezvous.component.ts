import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabRendezvous } from 'app/shared/model/tab-rendezvous.model';
import { TabRendezvousService } from './tab-rendezvous.service';
import { TabRendezvousDeleteDialogComponent } from './tab-rendezvous-delete-dialog.component';

@Component({
  selector: 'jhi-tab-rendezvous',
  templateUrl: './tab-rendezvous.component.html',
})
export class TabRendezvousComponent implements OnInit, OnDestroy {
  tabRendezvous?: ITabRendezvous[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabRendezvousService: TabRendezvousService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabRendezvousService.query().subscribe((res: HttpResponse<ITabRendezvous[]>) => (this.tabRendezvous = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabRendezvous();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabRendezvous): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabRendezvous(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabRendezvousListModification', () => this.loadAll());
  }

  delete(tabRendezvous: ITabRendezvous): void {
    const modalRef = this.modalService.open(TabRendezvousDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabRendezvous = tabRendezvous;
  }
}
