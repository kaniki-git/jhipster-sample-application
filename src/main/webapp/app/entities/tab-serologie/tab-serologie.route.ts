import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabSerologie, TabSerologie } from 'app/shared/model/tab-serologie.model';
import { TabSerologieService } from './tab-serologie.service';
import { TabSerologieComponent } from './tab-serologie.component';
import { TabSerologieDetailComponent } from './tab-serologie-detail.component';
import { TabSerologieUpdateComponent } from './tab-serologie-update.component';

@Injectable({ providedIn: 'root' })
export class TabSerologieResolve implements Resolve<ITabSerologie> {
  constructor(private service: TabSerologieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabSerologie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabSerologie: HttpResponse<TabSerologie>) => {
          if (tabSerologie.body) {
            return of(tabSerologie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabSerologie());
  }
}

export const tabSerologieRoute: Routes = [
  {
    path: '',
    component: TabSerologieComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSerologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabSerologieDetailComponent,
    resolve: {
      tabSerologie: TabSerologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSerologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabSerologieUpdateComponent,
    resolve: {
      tabSerologie: TabSerologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSerologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabSerologieUpdateComponent,
    resolve: {
      tabSerologie: TabSerologieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabSerologie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
