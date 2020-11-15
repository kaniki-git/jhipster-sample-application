import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabParamvitaux, TabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';
import { TabParamvitauxService } from './tab-paramvitaux.service';
import { TabParamvitauxComponent } from './tab-paramvitaux.component';
import { TabParamvitauxDetailComponent } from './tab-paramvitaux-detail.component';
import { TabParamvitauxUpdateComponent } from './tab-paramvitaux-update.component';

@Injectable({ providedIn: 'root' })
export class TabParamvitauxResolve implements Resolve<ITabParamvitaux> {
  constructor(private service: TabParamvitauxService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabParamvitaux> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabParamvitaux: HttpResponse<TabParamvitaux>) => {
          if (tabParamvitaux.body) {
            return of(tabParamvitaux.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabParamvitaux());
  }
}

export const tabParamvitauxRoute: Routes = [
  {
    path: '',
    component: TabParamvitauxComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamvitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabParamvitauxDetailComponent,
    resolve: {
      tabParamvitaux: TabParamvitauxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamvitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabParamvitauxUpdateComponent,
    resolve: {
      tabParamvitaux: TabParamvitauxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamvitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabParamvitauxUpdateComponent,
    resolve: {
      tabParamvitaux: TabParamvitauxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabParamvitaux.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
