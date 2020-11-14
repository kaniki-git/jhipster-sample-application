import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabPatient, TabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from './tab-patient.service';
import { TabPatientComponent } from './tab-patient.component';
import { TabPatientDetailComponent } from './tab-patient-detail.component';
import { TabPatientUpdateComponent } from './tab-patient-update.component';

@Injectable({ providedIn: 'root' })
export class TabPatientResolve implements Resolve<ITabPatient> {
  constructor(private service: TabPatientService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabPatient> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabPatient: HttpResponse<TabPatient>) => {
          if (tabPatient.body) {
            return of(tabPatient.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabPatient());
  }
}

export const tabPatientRoute: Routes = [
  {
    path: '',
    component: TabPatientComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabPatientDetailComponent,
    resolve: {
      tabPatient: TabPatientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabPatientUpdateComponent,
    resolve: {
      tabPatient: TabPatientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabPatientUpdateComponent,
    resolve: {
      tabPatient: TabPatientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabPatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
