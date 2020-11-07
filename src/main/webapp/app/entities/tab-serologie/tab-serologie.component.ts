import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabSerologie } from 'app/shared/model/tab-serologie.model';
import { TabSerologieService } from './tab-serologie.service';
import { TabSerologieDeleteDialogComponent } from './tab-serologie-delete-dialog.component';

@Component({
  selector: 'jhi-tab-serologie',
  templateUrl: './tab-serologie.component.html',
})
export class TabSerologieComponent implements OnInit, OnDestroy {
  tabSerologies?: ITabSerologie[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabSerologieService: TabSerologieService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabSerologieService.query().subscribe((res: HttpResponse<ITabSerologie[]>) => (this.tabSerologies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabSerologies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabSerologie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabSerologies(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabSerologieListModification', () => this.loadAll());
  }

  delete(tabSerologie: ITabSerologie): void {
    const modalRef = this.modalService.open(TabSerologieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabSerologie = tabSerologie;
  }
}
