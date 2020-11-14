import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabPatientComponent } from './tab-patient.component';
import { TabPatientDetailComponent } from './tab-patient-detail.component';
import { TabPatientUpdateComponent } from './tab-patient-update.component';
import { TabPatientDeleteDialogComponent } from './tab-patient-delete-dialog.component';
import { tabPatientRoute } from './tab-patient.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabPatientRoute)],
  declarations: [TabPatientComponent, TabPatientDetailComponent, TabPatientUpdateComponent, TabPatientDeleteDialogComponent],
  entryComponents: [TabPatientDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabPatientModule {}
