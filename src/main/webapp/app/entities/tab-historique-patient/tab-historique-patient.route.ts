import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabHistoriquePatient, TabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';
import { TabHistoriquePatientService } from './tab-historique-patient.service';
import { TabHistoriquePatientComponent } from './tab-historique-patient.component';
import { TabHistoriquePatientDetailComponent } from './tab-historique-patient-detail.component';
import { TabHistoriquePatientUpdateComponent } from './tab-historique-patient-update.component';

@Injectable({ providedIn: 'root' })
export class TabHistoriquePatientResolve implements Resolve<ITabHistoriquePatient> {
  constructor(private service: TabHistoriquePatientService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabHistoriquePatient> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabHistoriquePatient: HttpResponse<TabHistoriquePatient>) => {
          if (tabHistoriquePatient.body) {
            return of(tabHistoriquePatient.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabHistoriquePatient());
  }
}

export const tabHistoriquePatientRoute: Routes = [
  {
    path: '',
    component: TabHistoriquePatientComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHistoriquePatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabHistoriquePatientDetailComponent,
    resolve: {
      tabHistoriquePatient: TabHistoriquePatientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHistoriquePatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabHistoriquePatientUpdateComponent,
    resolve: {
      tabHistoriquePatient: TabHistoriquePatientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHistoriquePatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabHistoriquePatientUpdateComponent,
    resolve: {
      tabHistoriquePatient: TabHistoriquePatientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabHistoriquePatient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
