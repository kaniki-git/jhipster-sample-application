import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabConsultation, TabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from './tab-consultation.service';
import { TabConsultationComponent } from './tab-consultation.component';
import { TabConsultationDetailComponent } from './tab-consultation-detail.component';
import { TabConsultationUpdateComponent } from './tab-consultation-update.component';

@Injectable({ providedIn: 'root' })
export class TabConsultationResolve implements Resolve<ITabConsultation> {
  constructor(private service: TabConsultationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabConsultation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabConsultation: HttpResponse<TabConsultation>) => {
          if (tabConsultation.body) {
            return of(tabConsultation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabConsultation());
  }
}

export const tabConsultationRoute: Routes = [
  {
    path: '',
    component: TabConsultationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsultation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabConsultationDetailComponent,
    resolve: {
      tabConsultation: TabConsultationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsultation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabConsultationUpdateComponent,
    resolve: {
      tabConsultation: TabConsultationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsultation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabConsultationUpdateComponent,
    resolve: {
      tabConsultation: TabConsultationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabConsultation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
