import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from './tab-gynecologie.service';
import { TabGynecologieDeleteDialogComponent } from './tab-gynecologie-delete-dialog.component';

@Component({
  selector: 'jhi-tab-gynecologie',
  templateUrl: './tab-gynecologie.component.html',
})
export class TabGynecologieComponent implements OnInit, OnDestroy {
  tabGynecologies?: ITabGynecologie[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabGynecologieService: TabGynecologieService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabGynecologieService.query().subscribe((res: HttpResponse<ITabGynecologie[]>) => (this.tabGynecologies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabGynecologies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabGynecologie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabGynecologies(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabGynecologieListModification', () => this.loadAll());
  }

  delete(tabGynecologie: ITabGynecologie): void {
    const modalRef = this.modalService.open(TabGynecologieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabGynecologie = tabGynecologie;
  }
}
