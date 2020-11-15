import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabExamphysComponent } from './tab-examphys.component';
import { TabExamphysDetailComponent } from './tab-examphys-detail.component';
import { TabExamphysUpdateComponent } from './tab-examphys-update.component';
import { TabExamphysDeleteDialogComponent } from './tab-examphys-delete-dialog.component';
import { tabExamphysRoute } from './tab-examphys.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabExamphysRoute)],
  declarations: [TabExamphysComponent, TabExamphysDetailComponent, TabExamphysUpdateComponent, TabExamphysDeleteDialogComponent],
  entryComponents: [TabExamphysDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabExamphysModule {}
