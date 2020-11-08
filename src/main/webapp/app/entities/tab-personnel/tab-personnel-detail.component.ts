import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';

@Component({
  selector: 'jhi-tab-personnel-detail',
  templateUrl: './tab-personnel-detail.component.html',
})
export class TabPersonnelDetailComponent implements OnInit {
  tabPersonnel: ITabPersonnel | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabPersonnel }) => (this.tabPersonnel = tabPersonnel));
  }

  previousState(): void {
    window.history.back();
  }
}
