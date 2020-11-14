import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabExamPhys } from 'app/shared/model/tab-exam-phys.model';

@Component({
  selector: 'jhi-tab-exam-phys-detail',
  templateUrl: './tab-exam-phys-detail.component.html',
})
export class TabExamPhysDetailComponent implements OnInit {
  tabExamPhys: ITabExamPhys | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabExamPhys }) => (this.tabExamPhys = tabExamPhys));
  }

  previousState(): void {
    window.history.back();
  }
}
