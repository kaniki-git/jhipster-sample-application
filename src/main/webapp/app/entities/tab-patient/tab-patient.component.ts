import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from './tab-patient.service';
import { TabPatientDeleteDialogComponent } from './tab-patient-delete-dialog.component';

@Component({
  selector: 'jhi-tab-patient',
  templateUrl: './tab-patient.component.html',
})
export class TabPatientComponent implements OnInit, OnDestroy {
  tabPatients?: ITabPatient[];
  eventSubscriber?: Subscription;

  constructor(protected tabPatientService: TabPatientService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tabPatientService.query().subscribe((res: HttpResponse<ITabPatient[]>) => (this.tabPatients = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabPatients();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabPatient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabPatients(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabPatientListModification', () => this.loadAll());
  }

  delete(tabPatient: ITabPatient): void {
    const modalRef = this.modalService.open(TabPatientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabPatient = tabPatient;
  }
}
