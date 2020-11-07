import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabParamVitaux, TabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { TabParamVitauxService } from './tab-param-vitaux.service';
import { TabParamVitauxComponent } from './tab-param-vitaux.component';
import { TabParamVitauxDetailComponent } from './tab-param-vitaux-detail.component';
import { TabParamVitauxUpdateComponent } from './tab-param-vitaux-update.component';

@Injectable({ providedIn: 'root' })
export class TabParamVitauxResolve implements Resolve<ITabParamVitaux> {
  constructor(private service: TabParamVitauxService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabParamVitaux> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabParamVitaux: HttpResponse<TabParamVitaux>) => {
          if (tabParamVitaux.body) {
            return of(tabParamVitaux.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabParamVitaux());
  }
}

export const tabParamVitauxRoute: Routes = [
  {
    path: '',
    component: TabParamVitauxComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamVitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabParamVitauxDetailComponent,
    resolve: {
      tabParamVitaux: TabParamVitauxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamVitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabParamVitauxUpdateComponent,
    resolve: {
      tabParamVitaux: TabParamVitauxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamVitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabParamVitauxUpdateComponent,
    resolve: {
      tabParamVitaux: TabParamVitauxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamVitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
