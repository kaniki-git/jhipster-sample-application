import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabHospitalComponent } from './tab-hospital.component';
import { TabHospitalDetailComponent } from './tab-hospital-detail.component';
import { TabHospitalUpdateComponent } from './tab-hospital-update.component';
import { TabHospitalDeleteDialogComponent } from './tab-hospital-delete-dialog.component';
import { tabHospitalRoute } from './tab-hospital.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabHospitalRoute)],
  declarations: [TabHospitalComponent, TabHospitalDetailComponent, TabHospitalUpdateComponent, TabHospitalDeleteDialogComponent],
  entryComponents: [TabHospitalDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabHospitalModule {}
