import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabCoordonnee, TabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { TabCoordonneeService } from './tab-coordonnee.service';
import { TabCoordonneeComponent } from './tab-coordonnee.component';
import { TabCoordonneeDetailComponent } from './tab-coordonnee-detail.component';
import { TabCoordonneeUpdateComponent } from './tab-coordonnee-update.component';

@Injectable({ providedIn: 'root' })
export class TabCoordonneeResolve implements Resolve<ITabCoordonnee> {
  constructor(private service: TabCoordonneeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabCoordonnee> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabCoordonnee: HttpResponse<TabCoordonnee>) => {
          if (tabCoordonnee.body) {
            return of(tabCoordonnee.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabCoordonnee());
  }
}

export const tabCoordonneeRoute: Routes = [
  {
    path: '',
    component: TabCoordonneeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabCoordonnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabCoordonneeDetailComponent,
    resolve: {
      tabCoordonnee: TabCoordonneeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabCoordonnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabCoordonneeUpdateComponent,
    resolve: {
      tabCoordonnee: TabCoordonneeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabCoordonnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabCoordonneeUpdateComponent,
    resolve: {
      tabCoordonnee: TabCoordonneeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabCoordonnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
