import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabRefPaysComponent } from './tab-ref-pays.component';
import { TabRefPaysDetailComponent } from './tab-ref-pays-detail.component';
import { TabRefPaysUpdateComponent } from './tab-ref-pays-update.component';
import { TabRefPaysDeleteDialogComponent } from './tab-ref-pays-delete-dialog.component';
import { tabRefPaysRoute } from './tab-ref-pays.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabRefPaysRoute)],
  declarations: [TabRefPaysComponent, TabRefPaysDetailComponent, TabRefPaysUpdateComponent, TabRefPaysDeleteDialogComponent],
  entryComponents: [TabRefPaysDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabRefPaysModule {}
