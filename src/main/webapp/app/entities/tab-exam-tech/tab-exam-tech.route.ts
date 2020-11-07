import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabExamTech, TabExamTech } from 'app/shared/model/tab-exam-tech.model';
import { TabExamTechService } from './tab-exam-tech.service';
import { TabExamTechComponent } from './tab-exam-tech.component';
import { TabExamTechDetailComponent } from './tab-exam-tech-detail.component';
import { TabExamTechUpdateComponent } from './tab-exam-tech-update.component';

@Injectable({ providedIn: 'root' })
export class TabExamTechResolve implements Resolve<ITabExamTech> {
  constructor(private service: TabExamTechService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabExamTech> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabExamTech: HttpResponse<TabExamTech>) => {
          if (tabExamTech.body) {
            return of(tabExamTech.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabExamTech());
  }
}

export const tabExamTechRoute: Routes = [
  {
    path: '',
    component: TabExamTechComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamTech.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabExamTechDetailComponent,
    resolve: {
      tabExamTech: TabExamTechResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamTech.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabExamTechUpdateComponent,
    resolve: {
      tabExamTech: TabExamTechResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamTech.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabExamTechUpdateComponent,
    resolve: {
      tabExamTech: TabExamTechResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamTech.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
