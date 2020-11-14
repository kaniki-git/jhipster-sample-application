import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabUserComponent } from './tab-user.component';
import { TabUserDetailComponent } from './tab-user-detail.component';
import { TabUserUpdateComponent } from './tab-user-update.component';
import { TabUserDeleteDialogComponent } from './tab-user-delete-dialog.component';
import { tabUserRoute } from './tab-user.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabUserRoute)],
  declarations: [TabUserComponent, TabUserDetailComponent, TabUserUpdateComponent, TabUserDeleteDialogComponent],
  entryComponents: [TabUserDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabUserModule {}
