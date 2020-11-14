import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabPatient } from 'app/shared/model/tab-patient.model';

@Component({
  selector: 'jhi-tab-patient-detail',
  templateUrl: './tab-patient-detail.component.html',
})
export class TabPatientDetailComponent implements OnInit {
  tabPatient: ITabPatient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabPatient }) => (this.tabPatient = tabPatient));
  }

  previousState(): void {
    window.history.back();
  }
}
