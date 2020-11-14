import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabSpecialite } from 'app/shared/model/tab-specialite.model';

@Component({
  selector: 'jhi-tab-specialite-detail',
  templateUrl: './tab-specialite-detail.component.html',
})
export class TabSpecialiteDetailComponent implements OnInit {
  tabSpecialite: ITabSpecialite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabSpecialite }) => (this.tabSpecialite = tabSpecialite));
  }

  previousState(): void {
    window.history.back();
  }
}
