import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabAdministratif, TabAdministratif } from 'app/shared/model/tab-administratif.model';
import { TabAdministratifService } from './tab-administratif.service';
import { TabAdministratifComponent } from './tab-administratif.component';
import { TabAdministratifDetailComponent } from './tab-administratif-detail.component';
import { TabAdministratifUpdateComponent } from './tab-administratif-update.component';

@Injectable({ providedIn: 'root' })
export class TabAdministratifResolve implements Resolve<ITabAdministratif> {
  constructor(private service: TabAdministratifService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabAdministratif> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabAdministratif: HttpResponse<TabAdministratif>) => {
          if (tabAdministratif.body) {
            return of(tabAdministratif.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabAdministratif());
  }
}

export const tabAdministratifRoute: Routes = [
  {
    path: '',
    component: TabAdministratifComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAdministratif.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabAdministratifDetailComponent,
    resolve: {
      tabAdministratif: TabAdministratifResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAdministratif.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabAdministratifUpdateComponent,
    resolve: {
      tabAdministratif: TabAdministratifResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAdministratif.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabAdministratifUpdateComponent,
    resolve: {
      tabAdministratif: TabAdministratifResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabAdministratif.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
