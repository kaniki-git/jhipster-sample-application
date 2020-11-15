import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabUser } from 'app/shared/model/tab-user.model';
import { TabUserService } from './tab-user.service';
import { TabUserDeleteDialogComponent } from './tab-user-delete-dialog.component';

@Component({
  selector: 'jhi-tab-user',
  templateUrl: './tab-user.component.html',
})
export class TabUserComponent implements OnInit, OnDestroy {
  tabUsers?: ITabUser[];
  eventSubscriber?: Subscription;

  constructor(protected tabUserService: TabUserService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tabUserService.query().subscribe((res: HttpResponse<ITabUser[]>) => (this.tabUsers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabUsers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabUser): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabUsers(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabUserListModification', () => this.loadAll());
  }

  delete(tabUser: ITabUser): void {
    const modalRef = this.modalService.open(TabUserDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabUser = tabUser;
  }
}
