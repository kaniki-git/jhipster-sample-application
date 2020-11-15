import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabHospital, TabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from './tab-hospital.service';
import { TabHospitalComponent } from './tab-hospital.component';
import { TabHospitalDetailComponent } from './tab-hospital-detail.component';
import { TabHospitalUpdateComponent } from './tab-hospital-update.component';

@Injectable({ providedIn: 'root' })
export class TabHospitalResolve implements Resolve<ITabHospital> {
  constructor(private service: TabHospitalService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabHospital> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabHospital: HttpResponse<TabHospital>) => {
          if (tabHospital.body) {
            return of(tabHospital.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabHospital());
  }
}

export const tabHospitalRoute: Routes = [
  {
    path: '',
    component: TabHospitalComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHospital.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabHospitalDetailComponent,
    resolve: {
      tabHospital: TabHospitalResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHospital.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabHospitalUpdateComponent,
    resolve: {
      tabHospital: TabHospitalResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHospital.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabHospitalUpdateComponent,
    resolve: {
      tabHospital: TabHospitalResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHospital.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
