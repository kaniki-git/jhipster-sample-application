import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';

@Component({
  selector: 'jhi-tab-param-vitaux-detail',
  templateUrl: './tab-param-vitaux-detail.component.html',
})
export class TabParamVitauxDetailComponent implements OnInit {
  tabParamVitaux: ITabParamVitaux | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabParamVitaux }) => (this.tabParamVitaux = tabParamVitaux));
  }

  previousState(): void {
    window.history.back();
  }
}
