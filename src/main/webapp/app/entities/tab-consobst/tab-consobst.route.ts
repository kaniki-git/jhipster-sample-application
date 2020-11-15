import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabConsobst, TabConsobst } from 'app/shared/model/tab-consobst.model';
import { TabConsobstService } from './tab-consobst.service';
import { TabConsobstComponent } from './tab-consobst.component';
import { TabConsobstDetailComponent } from './tab-consobst-detail.component';
import { TabConsobstUpdateComponent } from './tab-consobst-update.component';

@Injectable({ providedIn: 'root' })
export class TabConsobstResolve implements Resolve<ITabConsobst> {
  constructor(private service: TabConsobstService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabConsobst> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabConsobst: HttpResponse<TabConsobst>) => {
          if (tabConsobst.body) {
            return of(tabConsobst.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabConsobst());
  }
}

export const tabConsobstRoute: Routes = [
  {
    path: '',
    component: TabConsobstComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsobst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabConsobstDetailComponent,
    resolve: {
      tabConsobst: TabConsobstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsobst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabConsobstUpdateComponent,
    resolve: {
      tabConsobst: TabConsobstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsobst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabConsobstUpdateComponent,
    resolve: {
      tabConsobst: TabConsobstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsobst.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
