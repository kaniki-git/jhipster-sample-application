import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';
import { TabAdministratifService } from './tab-administratif.service';
import { TabAdministratifDeleteDialogComponent } from './tab-administratif-delete-dialog.component';

@Component({
  selector: 'jhi-tab-administratif',
  templateUrl: './tab-administratif.component.html',
})
export class TabAdministratifComponent implements OnInit, OnDestroy {
  tabAdministratifs?: ITabAdministratif[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabAdministratifService: TabAdministratifService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabAdministratifService.query().subscribe((res: HttpResponse<ITabAdministratif[]>) => (this.tabAdministratifs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabAdministratifs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabAdministratif): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabAdministratifs(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabAdministratifListModification', () => this.loadAll());
  }

  delete(tabAdministratif: ITabAdministratif): void {
    const modalRef = this.modalService.open(TabAdministratifDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabAdministratif = tabAdministratif;
  }
}
