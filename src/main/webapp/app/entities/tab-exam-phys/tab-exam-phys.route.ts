import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabExamPhys, TabExamPhys } from 'app/shared/model/tab-exam-phys.model';
import { TabExamPhysService } from './tab-exam-phys.service';
import { TabExamPhysComponent } from './tab-exam-phys.component';
import { TabExamPhysDetailComponent } from './tab-exam-phys-detail.component';
import { TabExamPhysUpdateComponent } from './tab-exam-phys-update.component';

@Injectable({ providedIn: 'root' })
export class TabExamPhysResolve implements Resolve<ITabExamPhys> {
  constructor(private service: TabExamPhysService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabExamPhys> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabExamPhys: HttpResponse<TabExamPhys>) => {
          if (tabExamPhys.body) {
            return of(tabExamPhys.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabExamPhys());
  }
}

export const tabExamPhysRoute: Routes = [
  {
    path: '',
    component: TabExamPhysComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamPhys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabExamPhysDetailComponent,
    resolve: {
      tabExamPhys: TabExamPhysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamPhys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabExamPhysUpdateComponent,
    resolve: {
      tabExamPhys: TabExamPhysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamPhys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabExamPhysUpdateComponent,
    resolve: {
      tabExamPhys: TabExamPhysResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabExamPhys.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
