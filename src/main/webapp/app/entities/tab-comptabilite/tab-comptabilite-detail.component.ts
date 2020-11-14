import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabComptabilite } from 'app/shared/model/tab-comptabilite.model';

@Component({
  selector: 'jhi-tab-comptabilite-detail',
  templateUrl: './tab-comptabilite-detail.component.html',
})
export class TabComptabiliteDetailComponent implements OnInit {
  tabComptabilite: ITabComptabilite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabComptabilite }) => (this.tabComptabilite = tabComptabilite));
  }

  previousState(): void {
    window.history.back();
  }
}
