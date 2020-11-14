import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabRendezvousComponent } from './tab-rendezvous.component';
import { TabRendezvousDetailComponent } from './tab-rendezvous-detail.component';
import { TabRendezvousUpdateComponent } from './tab-rendezvous-update.component';
import { TabRendezvousDeleteDialogComponent } from './tab-rendezvous-delete-dialog.component';
import { tabRendezvousRoute } from './tab-rendezvous.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabRendezvousRoute)],
  declarations: [TabRendezvousComponent, TabRendezvousDetailComponent, TabRendezvousUpdateComponent, TabRendezvousDeleteDialogComponent],
  entryComponents: [TabRendezvousDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabRendezvousModule {}
