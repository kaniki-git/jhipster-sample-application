import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabSerologie } from 'app/shared/model/tab-serologie.model';

@Component({
  selector: 'jhi-tab-serologie-detail',
  templateUrl: './tab-serologie-detail.component.html',
})
export class TabSerologieDetailComponent implements OnInit {
  tabSerologie: ITabSerologie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabSerologie }) => (this.tabSerologie = tabSerologie));
  }

  previousState(): void {
    window.history.back();
  }
}
