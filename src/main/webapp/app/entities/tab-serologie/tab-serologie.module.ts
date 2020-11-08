import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabSerologieComponent } from './tab-serologie.component';
import { TabSerologieDetailComponent } from './tab-serologie-detail.component';
import { TabSerologieUpdateComponent } from './tab-serologie-update.component';
import { TabSerologieDeleteDialogComponent } from './tab-serologie-delete-dialog.component';
import { tabSerologieRoute } from './tab-serologie.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabSerologieRoute)],
  declarations: [TabSerologieComponent, TabSerologieDetailComponent, TabSerologieUpdateComponent, TabSerologieDeleteDialogComponent],
  entryComponents: [TabSerologieDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabSerologieModule {}
