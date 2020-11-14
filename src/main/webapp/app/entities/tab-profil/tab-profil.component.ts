import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabProfil } from 'app/shared/model/tab-profil.model';
import { TabProfilService } from './tab-profil.service';
import { TabProfilDeleteDialogComponent } from './tab-profil-delete-dialog.component';

@Component({
  selector: 'jhi-tab-profil',
  templateUrl: './tab-profil.component.html',
})
export class TabProfilComponent implements OnInit, OnDestroy {
  tabProfils?: ITabProfil[];
  eventSubscriber?: Subscription;

  constructor(protected tabProfilService: TabProfilService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tabProfilService.query().subscribe((res: HttpResponse<ITabProfil[]>) => (this.tabProfils = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabProfils();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabProfil): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabProfils(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabProfilListModification', () => this.loadAll());
  }

  delete(tabProfil: ITabProfil): void {
    const modalRef = this.modalService.open(TabProfilDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabProfil = tabProfil;
  }
}
