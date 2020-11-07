import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabGrossesseComponent } from './tab-grossesse.component';
import { TabGrossesseDetailComponent } from './tab-grossesse-detail.component';
import { TabGrossesseUpdateComponent } from './tab-grossesse-update.component';
import { TabGrossesseDeleteDialogComponent } from './tab-grossesse-delete-dialog.component';
import { tabGrossesseRoute } from './tab-grossesse.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabGrossesseRoute)],
  declarations: [TabGrossesseComponent, TabGrossesseDetailComponent, TabGrossesseUpdateComponent, TabGrossesseDeleteDialogComponent],
  entryComponents: [TabGrossesseDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabGrossesseModule {}
