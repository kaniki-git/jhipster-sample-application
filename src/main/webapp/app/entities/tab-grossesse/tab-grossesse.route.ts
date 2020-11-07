import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabGrossesse, TabGrossesse } from 'app/shared/model/tab-grossesse.model';
import { TabGrossesseService } from './tab-grossesse.service';
import { TabGrossesseComponent } from './tab-grossesse.component';
import { TabGrossesseDetailComponent } from './tab-grossesse-detail.component';
import { TabGrossesseUpdateComponent } from './tab-grossesse-update.component';

@Injectable({ providedIn: 'root' })
export class TabGrossesseResolve implements Resolve<ITabGrossesse> {
  constructor(private service: TabGrossesseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabGrossesse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabGrossesse: HttpResponse<TabGrossesse>) => {
          if (tabGrossesse.body) {
            return of(tabGrossesse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabGrossesse());
  }
}

export const tabGrossesseRoute: Routes = [
  {
    path: '',
    component: TabGrossesseComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGrossesse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabGrossesseDetailComponent,
    resolve: {
      tabGrossesse: TabGrossesseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGrossesse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabGrossesseUpdateComponent,
    resolve: {
      tabGrossesse: TabGrossesseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGrossesse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabGrossesseUpdateComponent,
    resolve: {
      tabGrossesse: TabGrossesseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGrossesse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
