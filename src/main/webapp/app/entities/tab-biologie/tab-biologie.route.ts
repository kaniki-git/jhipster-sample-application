import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabBiologie, TabBiologie } from 'app/shared/model/tab-biologie.model';
import { TabBiologieService } from './tab-biologie.service';
import { TabBiologieComponent } from './tab-biologie.component';
import { TabBiologieDetailComponent } from './tab-biologie-detail.component';
import { TabBiologieUpdateComponent } from './tab-biologie-update.component';

@Injectable({ providedIn: 'root' })
export class TabBiologieResolve implements Resolve<ITabBiologie> {
  constructor(private service: TabBiologieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabBiologie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabBiologie: HttpResponse<TabBiologie>) => {
          if (tabBiologie.body) {
            return of(tabBiologie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabBiologie());
  }
}

export const tabBiologieRoute: Routes = [
  {
    path: '',
    component: TabBiologieComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabBiologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabBiologieDetailComponent,
    resolve: {
      tabBiologie: TabBiologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabBiologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabBiologieUpdateComponent,
    resolve: {
      tabBiologie: TabBiologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabBiologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabBiologieUpdateComponent,
    resolve: {
      tabBiologie: TabBiologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabBiologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
