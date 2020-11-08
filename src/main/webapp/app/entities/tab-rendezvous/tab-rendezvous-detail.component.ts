import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabRendezvous } from 'app/shared/model/tab-rendezvous.model';

@Component({
  selector: 'jhi-tab-rendezvous-detail',
  templateUrl: './tab-rendezvous-detail.component.html',
})
export class TabRendezvousDetailComponent implements OnInit {
  tabRendezvous: ITabRendezvous | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabRendezvous }) => (this.tabRendezvous = tabRendezvous));
  }

  previousState(): void {
    window.history.back();
  }
}
