import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';
import { TabParamvitauxService } from './tab-paramvitaux.service';
import { TabParamvitauxDeleteDialogComponent } from './tab-paramvitaux-delete-dialog.component';

@Component({
  selector: 'jhi-tab-paramvitaux',
  templateUrl: './tab-paramvitaux.component.html',
})
export class TabParamvitauxComponent implements OnInit, OnDestroy {
  tabParamvitauxes?: ITabParamvitaux[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabParamvitauxService: TabParamvitauxService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabParamvitauxService.query().subscribe((res: HttpResponse<ITabParamvitaux[]>) => (this.tabParamvitauxes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabParamvitauxes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabParamvitaux): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabParamvitauxes(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabParamvitauxListModification', () => this.loadAll());
  }

  delete(tabParamvitaux: ITabParamvitaux): void {
    const modalRef = this.modalService.open(TabParamvitauxDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabParamvitaux = tabParamvitaux;
  }
}
