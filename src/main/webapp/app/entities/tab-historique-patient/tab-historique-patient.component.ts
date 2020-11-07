import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';
import { TabHistoriquePatientService } from './tab-historique-patient.service';
import { TabHistoriquePatientDeleteDialogComponent } from './tab-historique-patient-delete-dialog.component';

@Component({
  selector: 'jhi-tab-historique-patient',
  templateUrl: './tab-historique-patient.component.html',
})
export class TabHistoriquePatientComponent implements OnInit, OnDestroy {
  tabHistoriquePatients?: ITabHistoriquePatient[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabHistoriquePatientService: TabHistoriquePatientService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabHistoriquePatientService
      .query()
      .subscribe((res: HttpResponse<ITabHistoriquePatient[]>) => (this.tabHistoriquePatients = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabHistoriquePatients();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabHistoriquePatient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabHistoriquePatients(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabHistoriquePatientListModification', () => this.loadAll());
  }

  delete(tabHistoriquePatient: ITabHistoriquePatient): void {
    const modalRef = this.modalService.open(TabHistoriquePatientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabHistoriquePatient = tabHistoriquePatient;
  }
}
