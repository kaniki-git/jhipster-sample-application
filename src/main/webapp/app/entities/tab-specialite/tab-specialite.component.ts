import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabSpecialite } from 'app/shared/model/tab-specialite.model';
import { TabSpecialiteService } from './tab-specialite.service';
import { TabSpecialiteDeleteDialogComponent } from './tab-specialite-delete-dialog.component';

@Component({
  selector: 'jhi-tab-specialite',
  templateUrl: './tab-specialite.component.html',
})
export class TabSpecialiteComponent implements OnInit, OnDestroy {
  tabSpecialites?: ITabSpecialite[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabSpecialiteService: TabSpecialiteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabSpecialiteService.query().subscribe((res: HttpResponse<ITabSpecialite[]>) => (this.tabSpecialites = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabSpecialites();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabSpecialite): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabSpecialites(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabSpecialiteListModification', () => this.loadAll());
  }

  delete(tabSpecialite: ITabSpecialite): void {
    const modalRef = this.modalService.open(TabSpecialiteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabSpecialite = tabSpecialite;
  }
}
