import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabUserProfil, TabUserProfil } from 'app/shared/model/tab-user-profil.model';
import { TabUserProfilService } from './tab-user-profil.service';
import { TabUserProfilComponent } from './tab-user-profil.component';
import { TabUserProfilDetailComponent } from './tab-user-profil-detail.component';
import { TabUserProfilUpdateComponent } from './tab-user-profil-update.component';

@Injectable({ providedIn: 'root' })
export class TabUserProfilResolve implements Resolve<ITabUserProfil> {
  constructor(private service: TabUserProfilService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabUserProfil> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabUserProfil: HttpResponse<TabUserProfil>) => {
          if (tabUserProfil.body) {
            return of(tabUserProfil.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabUserProfil());
  }
}

export const tabUserProfilRoute: Routes = [
  {
    path: '',
    component: TabUserProfilComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUserProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabUserProfilDetailComponent,
    resolve: {
      tabUserProfil: TabUserProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUserProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabUserProfilUpdateComponent,
    resolve: {
      tabUserProfil: TabUserProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUserProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabUserProfilUpdateComponent,
    resolve: {
      tabUserProfil: TabUserProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabUserProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
