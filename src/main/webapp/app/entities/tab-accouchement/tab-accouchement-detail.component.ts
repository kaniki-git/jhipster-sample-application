import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';

@Component({
  selector: 'jhi-tab-accouchement-detail',
  templateUrl: './tab-accouchement-detail.component.html',
})
export class TabAccouchementDetailComponent implements OnInit {
  tabAccouchement: ITabAccouchement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabAccouchement }) => (this.tabAccouchement = tabAccouchement));
  }

  previousState(): void {
    window.history.back();
  }
}
