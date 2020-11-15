import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabParamvitauxComponent } from './tab-paramvitaux.component';
import { TabParamvitauxDetailComponent } from './tab-paramvitaux-detail.component';
import { TabParamvitauxUpdateComponent } from './tab-paramvitaux-update.component';
import { TabParamvitauxDeleteDialogComponent } from './tab-paramvitaux-delete-dialog.component';
import { tabParamvitauxRoute } from './tab-paramvitaux.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabParamvitauxRoute)],
  declarations: [
    TabParamvitauxComponent,
    TabParamvitauxDetailComponent,
    TabParamvitauxUpdateComponent,
    TabParamvitauxDeleteDialogComponent,
  ],
  entryComponents: [TabParamvitauxDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabParamvitauxModule {}
