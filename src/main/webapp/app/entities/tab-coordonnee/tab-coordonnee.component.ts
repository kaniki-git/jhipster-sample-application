import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { TabCoordonneeService } from './tab-coordonnee.service';
import { TabCoordonneeDeleteDialogComponent } from './tab-coordonnee-delete-dialog.component';

@Component({
  selector: 'jhi-tab-coordonnee',
  templateUrl: './tab-coordonnee.component.html',
})
export class TabCoordonneeComponent implements OnInit, OnDestroy {
  tabCoordonnees?: ITabCoordonnee[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabCoordonneeService: TabCoordonneeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabCoordonneeService.query().subscribe((res: HttpResponse<ITabCoordonnee[]>) => (this.tabCoordonnees = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabCoordonnees();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabCoordonnee): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabCoordonnees(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabCoordonneeListModification', () => this.loadAll());
  }

  delete(tabCoordonnee: ITabCoordonnee): void {
    const modalRef = this.modalService.open(TabCoordonneeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabCoordonnee = tabCoordonnee;
  }
}
