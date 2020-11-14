import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabExamPhysComponent } from './tab-exam-phys.component';
import { TabExamPhysDetailComponent } from './tab-exam-phys-detail.component';
import { TabExamPhysUpdateComponent } from './tab-exam-phys-update.component';
import { TabExamPhysDeleteDialogComponent } from './tab-exam-phys-delete-dialog.component';
import { tabExamPhysRoute } from './tab-exam-phys.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabExamPhysRoute)],
  declarations: [TabExamPhysComponent, TabExamPhysDetailComponent, TabExamPhysUpdateComponent, TabExamPhysDeleteDialogComponent],
  entryComponents: [TabExamPhysDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabExamPhysModule {}
