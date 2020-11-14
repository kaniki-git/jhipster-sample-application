import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabHospital } from 'app/shared/model/tab-hospital.model';

@Component({
  selector: 'jhi-tab-hospital-detail',
  templateUrl: './tab-hospital-detail.component.html',
})
export class TabHospitalDetailComponent implements OnInit {
  tabHospital: ITabHospital | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabHospital }) => (this.tabHospital = tabHospital));
  }

  previousState(): void {
    window.history.back();
  }
}
