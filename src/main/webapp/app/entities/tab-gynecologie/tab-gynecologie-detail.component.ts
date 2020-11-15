import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';

@Component({
  selector: 'jhi-tab-gynecologie-detail',
  templateUrl: './tab-gynecologie-detail.component.html',
})
export class TabGynecologieDetailComponent implements OnInit {
  tabGynecologie: ITabGynecologie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabGynecologie }) => (this.tabGynecologie = tabGynecologie));
  }

  previousState(): void {
    window.history.back();
  }
}
