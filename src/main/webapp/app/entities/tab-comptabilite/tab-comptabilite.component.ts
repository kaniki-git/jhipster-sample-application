import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabComptabilite } from 'app/shared/model/tab-comptabilite.model';
import { TabComptabiliteService } from './tab-comptabilite.service';
import { TabComptabiliteDeleteDialogComponent } from './tab-comptabilite-delete-dialog.component';

@Component({
  selector: 'jhi-tab-comptabilite',
  templateUrl: './tab-comptabilite.component.html',
})
export class TabComptabiliteComponent implements OnInit, OnDestroy {
  tabComptabilites?: ITabComptabilite[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabComptabiliteService: TabComptabiliteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabComptabiliteService.query().subscribe((res: HttpResponse<ITabComptabilite[]>) => (this.tabComptabilites = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabComptabilites();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabComptabilite): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabComptabilites(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabComptabiliteListModification', () => this.loadAll());
  }

  delete(tabComptabilite: ITabComptabilite): void {
    const modalRef = this.modalService.open(TabComptabiliteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabComptabilite = tabComptabilite;
  }
}
