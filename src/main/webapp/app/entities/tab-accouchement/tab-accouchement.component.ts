import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';
import { TabAccouchementService } from './tab-accouchement.service';
import { TabAccouchementDeleteDialogComponent } from './tab-accouchement-delete-dialog.component';

@Component({
  selector: 'jhi-tab-accouchement',
  templateUrl: './tab-accouchement.component.html',
})
export class TabAccouchementComponent implements OnInit, OnDestroy {
  tabAccouchements?: ITabAccouchement[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabAccouchementService: TabAccouchementService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabAccouchementService.query().subscribe((res: HttpResponse<ITabAccouchement[]>) => (this.tabAccouchements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabAccouchements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabAccouchement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabAccouchements(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabAccouchementListModification', () => this.loadAll());
  }

  delete(tabAccouchement: ITabAccouchement): void {
    const modalRef = this.modalService.open(TabAccouchementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabAccouchement = tabAccouchement;
  }
}
