import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabConsultationComponent } from './tab-consultation.component';
import { TabConsultationDetailComponent } from './tab-consultation-detail.component';
import { TabConsultationUpdateComponent } from './tab-consultation-update.component';
import { TabConsultationDeleteDialogComponent } from './tab-consultation-delete-dialog.component';
import { tabConsultationRoute } from './tab-consultation.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabConsultationRoute)],
  declarations: [
    TabConsultationComponent,
    TabConsultationDetailComponent,
    TabConsultationUpdateComponent,
    TabConsultationDeleteDialogComponent,
  ],
  entryComponents: [TabConsultationDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabConsultationModule {}
