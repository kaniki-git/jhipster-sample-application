import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from './tab-hospital.service';
import { TabHospitalDeleteDialogComponent } from './tab-hospital-delete-dialog.component';

@Component({
  selector: 'jhi-tab-hospital',
  templateUrl: './tab-hospital.component.html',
})
export class TabHospitalComponent implements OnInit, OnDestroy {
  tabHospitals?: ITabHospital[];
  eventSubscriber?: Subscription;

  constructor(
    protected tabHospitalService: TabHospitalService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tabHospitalService.query().subscribe((res: HttpResponse<ITabHospital[]>) => (this.tabHospitals = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTabHospitals();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITabHospital): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTabHospitals(): void {
    this.eventSubscriber = this.eventManager.subscribe('tabHospitalListModification', () => this.loadAll());
  }

  delete(tabHospital: ITabHospital): void {
    const modalRef = this.modalService.open(TabHospitalDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tabHospital = tabHospital;
  }
}
