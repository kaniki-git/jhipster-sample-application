import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabConsobst } from 'app/shared/model/tab-consobst.model';

@Component({
  selector: 'jhi-tab-consobst-detail',
  templateUrl: './tab-consobst-detail.component.html',
})
export class TabConsobstDetailComponent implements OnInit {
  tabConsobst: ITabConsobst | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsobst }) => (this.tabConsobst = tabConsobst));
  }

  previousState(): void {
    window.history.back();
  }
}
