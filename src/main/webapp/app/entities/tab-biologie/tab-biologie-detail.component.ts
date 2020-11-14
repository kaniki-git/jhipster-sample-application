import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabBiologie } from 'app/shared/model/tab-biologie.model';

@Component({
  selector: 'jhi-tab-biologie-detail',
  templateUrl: './tab-biologie-detail.component.html',
})
export class TabBiologieDetailComponent implements OnInit {
  tabBiologie: ITabBiologie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabBiologie }) => (this.tabBiologie = tabBiologie));
  }

  previousState(): void {
    window.history.back();
  }
}
