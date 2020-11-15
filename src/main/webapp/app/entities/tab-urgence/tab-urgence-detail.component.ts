import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabUrgence } from 'app/shared/model/tab-urgence.model';

@Component({
  selector: 'jhi-tab-urgence-detail',
  templateUrl: './tab-urgence-detail.component.html',
})
export class TabUrgenceDetailComponent implements OnInit {
  tabUrgence: ITabUrgence | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUrgence }) => (this.tabUrgence = tabUrgence));
  }

  previousState(): void {
    window.history.back();
  }
}
