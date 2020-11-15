import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabPersonnel, TabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from './tab-personnel.service';
import { TabPersonnelComponent } from './tab-personnel.component';
import { TabPersonnelDetailComponent } from './tab-personnel-detail.component';
import { TabPersonnelUpdateComponent } from './tab-personnel-update.component';

@Injectable({ providedIn: 'root' })
export class TabPersonnelResolve implements Resolve<ITabPersonnel> {
  constructor(private service: TabPersonnelService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabPersonnel> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabPersonnel: HttpResponse<TabPersonnel>) => {
          if (tabPersonnel.body) {
            return of(tabPersonnel.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabPersonnel());
  }
}

export const tabPersonnelRoute: Routes = [
  {
    path: '',
    component: TabPersonnelComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabPersonnelDetailComponent,
    resolve: {
      tabPersonnel: TabPersonnelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabPersonnelUpdateComponent,
    resolve: {
      tabPersonnel: TabPersonnelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabPersonnelUpdateComponent,
    resolve: {
      tabPersonnel: TabPersonnelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
