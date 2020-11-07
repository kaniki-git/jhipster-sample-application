import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITabExamTech } from 'app/shared/model/tab-exam-tech.model';

@Component({
  selector: 'jhi-tab-exam-tech-detail',
  templateUrl: './tab-exam-tech-detail.component.html',
})
export class TabExamTechDetailComponent implements OnInit {
  tabExamTech: ITabExamTech | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabExamTech }) => (this.tabExamTech = tabExamTech));
  }

  previousState(): void {
    window.history.back();
  }
}
