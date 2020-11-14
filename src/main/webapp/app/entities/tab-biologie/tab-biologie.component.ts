import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabBiologie } from 'app/shared/model/tab-biologie.model';
import { TabBiologieService } from './tab-biologie.service';
import { TabBiologieDeleteDialogComponent } from './tab-biologie-delete-dialog.component';

@Component({
  selector: 'jhi-tab-biologie',
  templateUrl: './tab-biologie.component.html',
})
export class TabBiologieComponent implements OnInit, OnDestroy {
  tabBiologies?: ITabBiologie[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabBiologieService: TabBiologieService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabBiologieService.query().subscribe((res: HttpResponse<ITabBiologie[]>) => (this.tabBiologies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabBiologies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabBiologie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabBiologies(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabBiologieListModification', () => this.loadAll());
  }

  delete(tabBiologie: ITabBiologie): void {
    const modalRef = this.modalService.open(TabBiologieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabBiologie = tabBiologie;
  }
}
