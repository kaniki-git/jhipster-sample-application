import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabRefFonction, TabRefFonction } from 'app/shared/model/tab-ref-fonction.model';
import { TabRefFonctionService } from './tab-ref-fonction.service';
import { TabRefFonctionComponent } from './tab-ref-fonction.component';
import { TabRefFonctionDetailComponent } from './tab-ref-fonction-detail.component';
import { TabRefFonctionUpdateComponent } from './tab-ref-fonction-update.component';

@Injectable({ providedIn: 'root' })
export class TabRefFonctionResolve implements Resolve<ITabRefFonction> {
  constructor(private service: TabRefFonctionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabRefFonction> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabRefFonction: HttpResponse<TabRefFonction>) => {
          if (tabRefFonction.body) {
            return of(tabRefFonction.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabRefFonction());
  }
}

export const tabRefFonctionRoute: Routes = [
  {
    path: '',
    component: TabRefFonctionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefFonction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabRefFonctionDetailComponent,
    resolve: {
      tabRefFonction: TabRefFonctionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefFonction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabRefFonctionUpdateComponent,
    resolve: {
      tabRefFonction: TabRefFonctionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefFonction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabRefFonctionUpdateComponent,
    resolve: {
      tabRefFonction: TabRefFonctionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRefFonction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
