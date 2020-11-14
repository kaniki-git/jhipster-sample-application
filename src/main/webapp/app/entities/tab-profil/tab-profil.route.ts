import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabProfil, TabProfil } from 'app/shared/model/tab-profil.model';
import { TabProfilService } from './tab-profil.service';
import { TabProfilComponent } from './tab-profil.component';
import { TabProfilDetailComponent } from './tab-profil-detail.component';
import { TabProfilUpdateComponent } from './tab-profil-update.component';

@Injectable({ providedIn: 'root' })
export class TabProfilResolve implements Resolve<ITabProfil> {
  constructor(private service: TabProfilService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabProfil> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabProfil: HttpResponse<TabProfil>) => {
          if (tabProfil.body) {
            return of(tabProfil.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabProfil());
  }
}

export const tabProfilRoute: Routes = [
  {
    path: '',
    component: TabProfilComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabProfilDetailComponent,
    resolve: {
      tabProfil: TabProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabProfilUpdateComponent,
    resolve: {
      tabProfil: TabProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabProfilUpdateComponent,
    resolve: {
      tabProfil: TabProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
