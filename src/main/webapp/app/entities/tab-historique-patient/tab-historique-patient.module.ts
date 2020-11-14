import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabHistoriquePatientComponent } from './tab-historique-patient.component';
import { TabHistoriquePatientDetailComponent } from './tab-historique-patient-detail.component';
import { TabHistoriquePatientUpdateComponent } from './tab-historique-patient-update.component';
import { TabHistoriquePatientDeleteDialogComponent } from './tab-historique-patient-delete-dialog.component';
import { tabHistoriquePatientRoute } from './tab-historique-patient.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabHistoriquePatientRoute)],
  declarations: [
    TabHistoriquePatientComponent,
    TabHistoriquePatientDetailComponent,
    TabHistoriquePatientUpdateComponent,
    TabHistoriquePatientDeleteDialogComponent,
  ],
  entryComponents: [TabHistoriquePatientDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabHistoriquePatientModule {}
