import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

@Component({
  selector: 'jhi-tab-coordonnee-detail',
  templateUrl: './tab-coordonnee-detail.component.html',
})
export class TabCoordonneeDetailComponent implements OnInit {
  tabCoordonnee: ITabCoordonnee | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabCoordonnee }) => (this.tabCoordonnee = tabCoordonnee));
  }

  previousState(): void {
    window.history.back();
  }
}
