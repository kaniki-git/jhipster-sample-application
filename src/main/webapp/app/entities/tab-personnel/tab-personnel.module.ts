import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabPersonnelComponent } from './tab-personnel.component';
import { TabPersonnelDetailComponent } from './tab-personnel-detail.component';
import { TabPersonnelUpdateComponent } from './tab-personnel-update.component';
import { TabPersonnelDeleteDialogComponent } from './tab-personnel-delete-dialog.component';
import { tabPersonnelRoute } from './tab-personnel.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabPersonnelRoute)],
  declarations: [TabPersonnelComponent, TabPersonnelDetailComponent, TabPersonnelUpdateComponent, TabPersonnelDeleteDialogComponent],
  entryComponents: [TabPersonnelDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabPersonnelModule {}
