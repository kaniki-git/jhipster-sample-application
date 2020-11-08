import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { TabParamVitauxService } from './tab-param-vitaux.service';
import { TabParamVitauxDeleteDialogComponent } from './tab-param-vitaux-delete-dialog.component';

@Component({
  selector: 'jhi-tab-param-vitaux',
  templateUrl: './tab-param-vitaux.component.html',
})
export class TabParamVitauxComponent implements OnInit, OnDestroy {
  tabParamVitauxes?: ITabParamVitaux[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabParamVitauxService: TabParamVitauxService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabParamVitauxService.query().subscribe((res: HttpResponse<ITabParamVitaux[]>) => (this.tabParamVitauxes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabParamVitauxes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabParamVitaux): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabParamVitauxes(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabParamVitauxListModification', () => this.loadAll());
  }

  delete(tabParamVitaux: ITabParamVitaux): void {
    const modalRef = this.modalService.open(TabParamVitauxDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabParamVitaux = tabParamVitaux;
  }
}
