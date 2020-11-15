import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabExamphys } from 'app/shared/model/tab-examphys.model';

@Component({
  selector: 'jhi-tab-examphys-detail',
  templateUrl: './tab-examphys-detail.component.html',
})
export class TabExamphysDetailComponent implements OnInit {
  tabExamphys: ITabExamphys | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabExamphys }) => (this.tabExamphys = tabExamphys));
  }

  previousState(): void {
    window.history.back();
  }
}
