import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabRefFonction } from 'app/shared/model/tab-ref-fonction.model';
import { TabRefFonctionService } from './tab-ref-fonction.service';
import { TabRefFonctionDeleteDialogComponent } from './tab-ref-fonction-delete-dialog.component';

@Component({
  selector: 'jhi-tab-ref-fonction',
  templateUrl: './tab-ref-fonction.component.html',
})
export class TabRefFonctionComponent implements OnInit, OnDestroy {
  tabRefFonctions?: ITabRefFonction[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabRefFonctionService: TabRefFonctionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabRefFonctionService.query().subscribe((res: HttpResponse<ITabRefFonction[]>) => (this.tabRefFonctions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabRefFonctions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabRefFonction): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabRefFonctions(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabRefFonctionListModification', () => this.loadAll());
  }

  delete(tabRefFonction: ITabRefFonction): void {
    const modalRef = this.modalService.open(TabRefFonctionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabRefFonction = tabRefFonction;
  }
}
