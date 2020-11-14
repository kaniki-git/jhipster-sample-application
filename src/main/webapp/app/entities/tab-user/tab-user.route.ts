import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabUser, TabUser } from 'app/shared/model/tab-user.model';
import { TabUserService } from './tab-user.service';
import { TabUserComponent } from './tab-user.component';
import { TabUserDetailComponent } from './tab-user-detail.component';
import { TabUserUpdateComponent } from './tab-user-update.component';

@Injectable({ providedIn: 'root' })
export class TabUserResolve implements Resolve<ITabUser> {
  constructor(private service: TabUserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabUser: HttpResponse<TabUser>) => {
          if (tabUser.body) {
            return of(tabUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabUser());
  }
}

export const tabUserRoute: Routes = [
  {
    path: '',
    component: TabUserComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabUserDetailComponent,
    resolve: {
      tabUser: TabUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabUserUpdateComponent,
    resolve: {
      tabUser: TabUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabUserUpdateComponent,
    resolve: {
      tabUser: TabUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
