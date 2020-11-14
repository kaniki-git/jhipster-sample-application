import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabConsObstComponent } from './tab-cons-obst.component';
import { TabConsObstDetailComponent } from './tab-cons-obst-detail.component';
import { TabConsObstUpdateComponent } from './tab-cons-obst-update.component';
import { TabConsObstDeleteDialogComponent } from './tab-cons-obst-delete-dialog.component';
import { tabConsObstRoute } from './tab-cons-obst.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabConsObstRoute)],
  declarations: [TabConsObstComponent, TabConsObstDetailComponent, TabConsObstUpdateComponent, TabConsObstDeleteDialogComponent],
  entryComponents: [TabConsObstDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabConsObstModule {}
