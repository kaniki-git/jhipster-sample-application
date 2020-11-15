import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabAccouchementComponent } from './tab-accouchement.component';
import { TabAccouchementDetailComponent } from './tab-accouchement-detail.component';
import { TabAccouchementUpdateComponent } from './tab-accouchement-update.component';
import { TabAccouchementDeleteDialogComponent } from './tab-accouchement-delete-dialog.component';
import { tabAccouchementRoute } from './tab-accouchement.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabAccouchementRoute)],
  declarations: [
    TabAccouchementComponent,
    TabAccouchementDetailComponent,
    TabAccouchementUpdateComponent,
    TabAccouchementDeleteDialogComponent,
  ],
  entryComponents: [TabAccouchementDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabAccouchementModule {}
