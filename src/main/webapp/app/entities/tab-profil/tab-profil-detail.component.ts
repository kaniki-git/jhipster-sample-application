import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabProfil } from 'app/shared/model/tab-profil.model';

@Component({
  selector: 'jhi-tab-profil-detail',
  templateUrl: './tab-profil-detail.component.html',
})
export class TabProfilDetailComponent implements OnInit {
  tabProfil: ITabProfil | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabProfil }) => (this.tabProfil = tabProfil));
  }

  previousState(): void {
    window.history.back();
  }
}
