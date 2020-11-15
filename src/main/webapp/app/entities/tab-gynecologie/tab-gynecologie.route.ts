import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabGynecologie, TabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from './tab-gynecologie.service';
import { TabGynecologieComponent } from './tab-gynecologie.component';
import { TabGynecologieDetailComponent } from './tab-gynecologie-detail.component';
import { TabGynecologieUpdateComponent } from './tab-gynecologie-update.component';

@Injectable({ providedIn: 'root' })
export class TabGynecologieResolve implements Resolve<ITabGynecologie> {
  constructor(private service: TabGynecologieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabGynecologie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabGynecologie: HttpResponse<TabGynecologie>) => {
          if (tabGynecologie.body) {
            return of(tabGynecologie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabGynecologie());
  }
}

export const tabGynecologieRoute: Routes = [
  {
    path: '',
    component: TabGynecologieComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGynecologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabGynecologieDetailComponent,
    resolve: {
      tabGynecologie: TabGynecologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGynecologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabGynecologieUpdateComponent,
    resolve: {
      tabGynecologie: TabGynecologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGynecologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabGynecologieUpdateComponent,
    resolve: {
      tabGynecologie: TabGynecologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabGynecologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
