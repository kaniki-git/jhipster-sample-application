import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabSpecialiteComponent } from './tab-specialite.component';
import { TabSpecialiteDetailComponent } from './tab-specialite-detail.component';
import { TabSpecialiteUpdateComponent } from './tab-specialite-update.component';
import { TabSpecialiteDeleteDialogComponent } from './tab-specialite-delete-dialog.component';
import { tabSpecialiteRoute } from './tab-specialite.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabSpecialiteRoute)],
  declarations: [TabSpecialiteComponent, TabSpecialiteDetailComponent, TabSpecialiteUpdateComponent, TabSpecialiteDeleteDialogComponent],
  entryComponents: [TabSpecialiteDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabSpecialiteModule {}
