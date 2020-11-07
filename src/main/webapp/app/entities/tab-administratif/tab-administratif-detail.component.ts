import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';

@Component({
  selector: 'jhi-tab-administratif-detail',
  templateUrl: './tab-administratif-detail.component.html',
})
export class TabAdministratifDetailComponent implements OnInit {
  tabAdministratif: ITabAdministratif | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabAdministratif }) => (this.tabAdministratif = tabAdministratif));
  }

  previousState(): void {
    window.history.back();
  }
}
