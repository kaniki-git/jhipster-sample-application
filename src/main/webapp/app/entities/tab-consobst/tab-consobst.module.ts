import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabConsobstComponent } from './tab-consobst.component';
import { TabConsobstDetailComponent } from './tab-consobst-detail.component';
import { TabConsobstUpdateComponent } from './tab-consobst-update.component';
import { TabConsobstDeleteDialogComponent } from './tab-consobst-delete-dialog.component';
import { tabConsobstRoute } from './tab-consobst.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabConsobstRoute)],
  declarations: [TabConsobstComponent, TabConsobstDetailComponent, TabConsobstUpdateComponent, TabConsobstDeleteDialogComponent],
  entryComponents: [TabConsobstDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabConsobstModule {}
