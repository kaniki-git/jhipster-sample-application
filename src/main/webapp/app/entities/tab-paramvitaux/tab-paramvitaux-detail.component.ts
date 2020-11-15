import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';

@Component({
  selector: 'jhi-tab-paramvitaux-detail',
  templateUrl: './tab-paramvitaux-detail.component.html',
})
export class TabParamvitauxDetailComponent implements OnInit {
  tabParamvitaux: ITabParamvitaux | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabParamvitaux }) => (this.tabParamvitaux = tabParamvitaux));
  }

  previousState(): void {
    window.history.back();
  }
}
