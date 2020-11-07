import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITabRendezvous, TabRendezvous } from 'app/shared/model/tab-rendezvous.model';
import { TabRendezvousService } from './tab-rendezvous.service';
import { TabRendezvousComponent } from './tab-rendezvous.component';
import { TabRendezvousDetailComponent } from './tab-rendezvous-detail.component';
import { TabRendezvousUpdateComponent } from './tab-rendezvous-update.component';

@Injectable({ providedIn: 'root' })
export class TabRendezvousResolve implements Resolve<ITabRendezvous> {
  constructor(private service: TabRendezvousService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITabRendezvous> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tabRendezvous: HttpResponse<TabRendezvous>) => {
          if (tabRendezvous.body) {
            return of(tabRendezvous.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TabRendezvous());
  }
}

export const tabRendezvousRoute: Routes = [
  {
    path: '',
    component: TabRendezvousComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRendezvous.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TabRendezvousDetailComponent,
    resolve: {
      tabRendezvous: TabRendezvousResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRendezvous.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TabRendezvousUpdateComponent,
    resolve: {
      tabRendezvous: TabRendezvousResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRendezvous.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TabRendezvousUpdateComponent,
    resolve: {
      tabRendezvous: TabRendezvousResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.tabRendezvous.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
