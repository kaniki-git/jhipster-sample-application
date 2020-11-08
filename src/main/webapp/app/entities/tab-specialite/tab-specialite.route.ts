import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabSpecialite, TabSpecialite } from 'app/shared/model/tab-specialite.model';
import { TabSpecialiteService } from './tab-specialite.service';
import { TabSpecialiteComponent } from './tab-specialite.component';
import { TabSpecialiteDetailComponent } from './tab-specialite-detail.component';
import { TabSpecialiteUpdateComponent } from './tab-specialite-update.component';

@Injectable({ providedIn: 'root' })
export class TabSpecialiteResolve implements Resolve<ITabSpecialite> {
  constructor(private service: TabSpecialiteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabSpecialite> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabSpecialite: HttpResponse<TabSpecialite>) => {
          if (tabSpecialite.body) {
            return of(tabSpecialite.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabSpecialite());
  }
}

export const tabSpecialiteRoute: Routes = [
  {
    path: '',
    component: TabSpecialiteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabSpecialiteDetailComponent,
    resolve: {
      tabSpecialite: TabSpecialiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabSpecialiteUpdateComponent,
    resolve: {
      tabSpecialite: TabSpecialiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabSpecialiteUpdateComponent,
    resolve: {
      tabSpecialite: TabSpecialiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
