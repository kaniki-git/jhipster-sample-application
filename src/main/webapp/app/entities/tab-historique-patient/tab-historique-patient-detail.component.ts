import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';

@Component({
  selector: 'jhi-tab-historique-patient-detail',
  templateUrl: './tab-historique-patient-detail.component.html',
})
export class TabHistoriquePatientDetailComponent implements OnInit {
  tabHistoriquePatient: ITabHistoriquePatient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabHistoriquePatient }) => (this.tabHistoriquePatient = tabHistoriquePatient));
  }

  previousState(): void {
    window.history.back();
  }
}
