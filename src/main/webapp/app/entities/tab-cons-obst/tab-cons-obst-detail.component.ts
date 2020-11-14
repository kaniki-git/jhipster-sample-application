import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabConsObst } from 'app/shared/model/tab-cons-obst.model';

@Component({
  selector: 'jhi-tab-cons-obst-detail',
  templateUrl: './tab-cons-obst-detail.component.html',
})
export class TabConsObstDetailComponent implements OnInit {
  tabConsObst: ITabConsObst | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsObst }) => (this.tabConsObst = tabConsObst));
  }

  previousState(): void {
    window.history.back();
  }
}
