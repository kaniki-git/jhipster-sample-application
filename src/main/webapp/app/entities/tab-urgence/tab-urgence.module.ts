import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabUrgenceComponent } from './tab-urgence.component';
import { TabUrgenceDetailComponent } from './tab-urgence-detail.component';
import { TabUrgenceUpdateComponent } from './tab-urgence-update.component';
import { TabUrgenceDeleteDialogComponent } from './tab-urgence-delete-dialog.component';
import { tabUrgenceRoute } from './tab-urgence.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabUrgenceRoute)],
  declarations: [TabUrgenceComponent, TabUrgenceDetailComponent, TabUrgenceUpdateComponent, TabUrgenceDeleteDialogComponent],
  entryComponents: [TabUrgenceDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabUrgenceModule {}
