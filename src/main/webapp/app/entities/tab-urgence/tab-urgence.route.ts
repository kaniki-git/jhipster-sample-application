import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabUrgence, TabUrgence } from 'app/shared/model/tab-urgence.model';
import { TabUrgenceService } from './tab-urgence.service';
import { TabUrgenceComponent } from './tab-urgence.component';
import { TabUrgenceDetailComponent } from './tab-urgence-detail.component';
import { TabUrgenceUpdateComponent } from './tab-urgence-update.component';

@Injectable({ providedIn: 'root' })
export class TabUrgenceResolve implements Resolve<ITabUrgence> {
  constructor(private service: TabUrgenceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabUrgence> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabUrgence: HttpResponse<TabUrgence>) => {
          if (tabUrgence.body) {
            return of(tabUrgence.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabUrgence());
  }
}

export const tabUrgenceRoute: Routes = [
  {
    path: '',
    component: TabUrgenceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUrgence.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabUrgenceDetailComponent,
    resolve: {
      tabUrgence: TabUrgenceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUrgence.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabUrgenceUpdateComponent,
    resolve: {
      tabUrgence: TabUrgenceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUrgence.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabUrgenceUpdateComponent,
    resolve: {
      tabUrgence: TabUrgenceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUrgence.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
