import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabExamphys, TabExamphys } from 'app/shared/model/tab-examphys.model';
import { TabExamphysService } from './tab-examphys.service';
import { TabExamphysComponent } from './tab-examphys.component';
import { TabExamphysDetailComponent } from './tab-examphys-detail.component';
import { TabExamphysUpdateComponent } from './tab-examphys-update.component';

@Injectable({ providedIn: 'root' })
export class TabExamphysResolve implements Resolve<ITabExamphys> {
  constructor(private service: TabExamphysService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabExamphys> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabExamphys: HttpResponse<TabExamphys>) => {
          if (tabExamphys.body) {
            return of(tabExamphys.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabExamphys());
  }
}

export const tabExamphysRoute: Routes = [
  {
    path: '',
    component: TabExamphysComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamphys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabExamphysDetailComponent,
    resolve: {
      tabExamphys: TabExamphysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamphys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabExamphysUpdateComponent,
    resolve: {
      tabExamphys: TabExamphysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamphys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabExamphysUpdateComponent,
    resolve: {
      tabExamphys: TabExamphysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamphys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
