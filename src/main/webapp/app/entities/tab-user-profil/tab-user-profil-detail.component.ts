import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabUserProfil } from 'app/shared/model/tab-user-profil.model';

@Component({
  selector: 'jhi-tab-user-profil-detail',
  templateUrl: './tab-user-profil-detail.component.html',
})
export class TabUserProfilDetailComponent implements OnInit {
  tabUserProfil: ITabUserProfil | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUserProfil }) => (this.tabUserProfil = tabUserProfil));
  }

  previousState(): void {
    window.history.back();
  }
}
