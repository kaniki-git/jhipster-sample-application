import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabRefFonction } from 'app/shared/model/tab-ref-fonction.model';

@Component({
  selector: 'jhi-tab-ref-fonction-detail',
  templateUrl: './tab-ref-fonction-detail.component.html',
})
export class TabRefFonctionDetailComponent implements OnInit {
  tabRefFonction: ITabRefFonction | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabRefFonction }) => (this.tabRefFonction = tabRefFonction));
  }

  previousState(): void {
    window.history.back();
  }
}
