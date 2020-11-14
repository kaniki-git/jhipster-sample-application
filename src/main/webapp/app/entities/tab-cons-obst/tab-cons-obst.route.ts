import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabConsObst, TabConsObst } from 'app/shared/model/tab-cons-obst.model';
import { TabConsObstService } from './tab-cons-obst.service';
import { TabConsObstComponent } from './tab-cons-obst.component';
import { TabConsObstDetailComponent } from './tab-cons-obst-detail.component';
import { TabConsObstUpdateComponent } from './tab-cons-obst-update.component';

@Injectable({ providedIn: 'root' })
export class TabConsObstResolve implements Resolve<ITabConsObst> {
  constructor(private service: TabConsObstService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabConsObst> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabConsObst: HttpResponse<TabConsObst>) => {
          if (tabConsObst.body) {
            return of(tabConsObst.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabConsObst());
  }
}

export const tabConsObstRoute: Routes = [
  {
    path: '',
    component: TabConsObstComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsObst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabConsObstDetailComponent,
    resolve: {
      tabConsObst: TabConsObstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsObst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabConsObstUpdateComponent,
    resolve: {
      tabConsObst: TabConsObstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsObst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabConsObstUpdateComponent,
    resolve: {
      tabConsObst: TabConsObstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsObst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
