import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { TabUrgenceService } from './tab-urgence.service';
import { TabUrgenceDeleteDialogComponent } from './tab-urgence-delete-dialog.component';

@Component({
  selector: 'jhi-tab-urgence',
  templateUrl: './tab-urgence.component.html',
})
export class TabUrgenceComponent implements OnInit, OnDestroy {
  tabUrgences?: ITabUrgence[];
  eventSubscriber?: Subscription;

  constructor(protected tabUrgenceService: TabUrgenceService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tabUrgenceService.query().subscribe((res: HttpResponse<ITabUrgence[]>) => (this.tabUrgences = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabUrgences();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabUrgence): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabUrgences(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabUrgenceListModification', () => this.loadAll());
  }

  delete(tabUrgence: ITabUrgence): void {
    const modalRef = this.modalService.open(TabUrgenceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabUrgence = tabUrgence;
  }
}
