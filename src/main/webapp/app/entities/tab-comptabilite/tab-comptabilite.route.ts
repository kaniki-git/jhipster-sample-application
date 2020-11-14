import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabComptabilite, TabComptabilite } from 'app/shared/model/tab-comptabilite.model';
import { TabComptabiliteService } from './tab-comptabilite.service';
import { TabComptabiliteComponent } from './tab-comptabilite.component';
import { TabComptabiliteDetailComponent } from './tab-comptabilite-detail.component';
import { TabComptabiliteUpdateComponent } from './tab-comptabilite-update.component';

@Injectable({ providedIn: 'root' })
export class TabComptabiliteResolve implements Resolve<ITabComptabilite> {
  constructor(private service: TabComptabiliteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabComptabilite> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabComptabilite: HttpResponse<TabComptabilite>) => {
          if (tabComptabilite.body) {
            return of(tabComptabilite.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabComptabilite());
  }
}

export const tabComptabiliteRoute: Routes = [
  {
    path: '',
    component: TabComptabiliteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabComptabilite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabComptabiliteDetailComponent,
    resolve: {
      tabComptabilite: TabComptabiliteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabComptabilite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabComptabiliteUpdateComponent,
    resolve: {
      tabComptabilite: TabComptabiliteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabComptabilite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabComptabiliteUpdateComponent,
    resolve: {
      tabComptabilite: TabComptabiliteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabComptabilite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
