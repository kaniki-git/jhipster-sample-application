import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from './tab-consultation.service';
import { TabConsultationDeleteDialogComponent } from './tab-consultation-delete-dialog.component';

@Component({
  selector: 'jhi-tab-consultation',
  templateUrl: './tab-consultation.component.html',
})
export class TabConsultationComponent implements OnInit, OnDestroy {
  tabConsultations?: ITabConsultation[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabConsultationService: TabConsultationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabConsultations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabConsultations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabConsultation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabConsultations(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabConsultationListModification', () => this.loadAll());
  }

  delete(tabConsultation: ITabConsultation): void {
    const modalRef = this.modalService.open(TabConsultationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabConsultation = tabConsultation;
  }
}
