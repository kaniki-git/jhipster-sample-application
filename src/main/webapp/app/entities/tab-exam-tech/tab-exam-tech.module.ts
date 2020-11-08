import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabExamTechComponent } from './tab-exam-tech.component';
import { TabExamTechDetailComponent } from './tab-exam-tech-detail.component';
import { TabExamTechUpdateComponent } from './tab-exam-tech-update.component';
import { TabExamTechDeleteDialogComponent } from './tab-exam-tech-delete-dialog.component';
import { tabExamTechRoute } from './tab-exam-tech.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabExamTechRoute)],
  declarations: [TabExamTechComponent, TabExamTechDetailComponent, TabExamTechUpdateComponent, TabExamTechDeleteDialogComponent],
  entryComponents: [TabExamTechDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabExamTechModule {}
