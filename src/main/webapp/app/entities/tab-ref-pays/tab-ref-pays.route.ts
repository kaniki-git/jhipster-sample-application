import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabRefPays, TabRefPays } from 'app/shared/model/tab-ref-pays.model';
import { TabRefPaysService } from './tab-ref-pays.service';
import { TabRefPaysComponent } from './tab-ref-pays.component';
import { TabRefPaysDetailComponent } from './tab-ref-pays-detail.component';
import { TabRefPaysUpdateComponent } from './tab-ref-pays-update.component';

@Injectable({ providedIn: 'root' })
export class TabRefPaysResolve implements Resolve<ITabRefPays> {
  constructor(private service: TabRefPaysService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabRefPays> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabRefPays: HttpResponse<TabRefPays>) => {
          if (tabRefPays.body) {
            return of(tabRefPays.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabRefPays());
  }
}

export const tabRefPaysRoute: Routes = [
  {
    path: '',
    component: TabRefPaysComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefPays.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabRefPaysDetailComponent,
    resolve: {
      tabRefPays: TabRefPaysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefPays.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabRefPaysUpdateComponent,
    resolve: {
      tabRefPays: TabRefPaysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefPays.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabRefPaysUpdateComponent,
    resolve: {
      tabRefPays: TabRefPaysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefPays.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
