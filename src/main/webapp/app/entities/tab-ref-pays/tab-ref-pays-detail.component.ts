import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabRefPays } from 'app/shared/model/tab-ref-pays.model';

@Component({
  selector: 'jhi-tab-ref-pays-detail',
  templateUrl: './tab-ref-pays-detail.component.html',
})
export class TabRefPaysDetailComponent implements OnInit {
  tabRefPays: ITabRefPays | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabRefPays }) => (this.tabRefPays = tabRefPays));
  }

  previousState(): void {
    window.history.back();
  }
}
