import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from './tab-personnel.service';
import { TabPersonnelDeleteDialogComponent } from './tab-personnel-delete-dialog.component';

@Component({
  selector: 'jhi-tab-personnel',
  templateUrl: './tab-personnel.component.html',
})
export class TabPersonnelComponent implements OnInit, OnDestroy {
  tabPersonnels?: ITabPersonnel[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabPersonnelService: TabPersonnelService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabPersonnelService.query().subscribe((res: HttpResponse<ITabPersonnel[]>) => (this.tabPersonnels = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabPersonnels();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabPersonnel): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabPersonnels(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabPersonnelListModification', () => this.loadAll());
  }

  delete(tabPersonnel: ITabPersonnel): void {
    const modalRef = this.modalService.open(TabPersonnelDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabPersonnel = tabPersonnel;
  }
}
