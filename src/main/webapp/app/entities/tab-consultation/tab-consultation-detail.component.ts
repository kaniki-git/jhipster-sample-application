import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabConsultation } from 'app/shared/model/tab-consultation.model';

@Component({
  selector: 'jhi-tab-consultation-detail',
  templateUrl: './tab-consultation-detail.component.html',
})
export class TabConsultationDetailComponent implements OnInit {
  tabConsultation: ITabConsultation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsultation }) => (this.tabConsultation = tabConsultation));
  }

  previousState(): void {
    window.history.back();
  }
}
