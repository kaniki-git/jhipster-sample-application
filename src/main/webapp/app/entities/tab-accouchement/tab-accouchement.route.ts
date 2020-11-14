import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabAccouchement, TabAccouchement } from 'app/shared/model/tab-accouchement.model';
import { TabAccouchementService } from './tab-accouchement.service';
import { TabAccouchementComponent } from './tab-accouchement.component';
import { TabAccouchementDetailComponent } from './tab-accouchement-detail.component';
import { TabAccouchementUpdateComponent } from './tab-accouchement-update.component';

@Injectable({ providedIn: 'root' })
export class TabAccouchementResolve implements Resolve<ITabAccouchement> {
  constructor(private service: TabAccouchementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabAccouchement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabAccouchement: HttpResponse<TabAccouchement>) => {
          if (tabAccouchement.body) {
            return of(tabAccouchement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabAccouchement());
  }
}

export const tabAccouchementRoute: Routes = [
  {
    path: '',
    component: TabAccouchementComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAccouchement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabAccouchementDetailComponent,
    resolve: {
      tabAccouchement: TabAccouchementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAccouchement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabAccouchementUpdateComponent,
    resolve: {
      tabAccouchement: TabAccouchementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAccouchement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabAccouchementUpdateComponent,
    resolve: {
      tabAccouchement: TabAccouchementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAccouchement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
