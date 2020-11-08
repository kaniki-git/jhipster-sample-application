import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabUser } from 'app/shared/model/tab-user.model';

@Component({
  selector: 'jhi-tab-user-detail',
  templateUrl: './tab-user-detail.component.html',
})
export class TabUserDetailComponent implements OnInit {
  tabUser: ITabUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUser }) => (this.tabUser = tabUser));
  }

  previousState(): void {
    window.history.back();
  }
}
