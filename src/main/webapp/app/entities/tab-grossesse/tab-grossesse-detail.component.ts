import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabGrossesse } from 'app/shared/model/tab-grossesse.model';

@Component({
  selector: 'jhi-tab-grossesse-detail',
  templateUrl: './tab-grossesse-detail.component.html',
})
export class TabGrossesseDetailComponent implements OnInit {
  tabGrossesse: ITabGrossesse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabGrossesse }) => (this.tabGrossesse = tabGrossesse));
  }

  previousState(): void {
    window.history.back();
  }
}
