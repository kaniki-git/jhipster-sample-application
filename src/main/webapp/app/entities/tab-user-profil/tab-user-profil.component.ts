import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabUserProfil } from 'app/shared/model/tab-user-profil.model';
import { TabUserProfilService } from './tab-user-profil.service';
import { TabUserProfilDeleteDialogComponent } from './tab-user-profil-delete-dialog.component';

@Component({
  selector: 'jhi-tab-user-profil',
  templateUrl: './tab-user-profil.component.html',
})
export class TabUserProfilComponent implements OnInit, OnDestroy {
  tabUserProfils?: ITabUserProfil[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabUserProfilService: TabUserProfilService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabUserProfilService.query().subscribe((res: HttpResponse<ITabUserProfil[]>) => (this.tabUserProfils = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabUserProfils();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabUserProfil): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabUserProfils(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabUserProfilListModification', () => this.loadAll());
  }

  delete(tabUserProfil: ITabUserProfil): void {
    const modalRef = this.modalService.open(TabUserProfilDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabUserProfil = tabUserProfil;
  }
}
