import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabCoordonneeComponent } from './tab-coordonnee.component';
import { TabCoordonneeDetailComponent } from './tab-coordonnee-detail.component';
import { TabCoordonneeUpdateComponent } from './tab-coordonnee-update.component';
import { TabCoordonneeDeleteDialogComponent } from './tab-coordonnee-delete-dialog.component';
import { tabCoordonneeRoute } from './tab-coordonnee.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabCoordonneeRoute)],
  declarations: [TabCoordonneeComponent, TabCoordonneeDetailComponent, TabCoordonneeUpdateComponent, TabCoordonneeDeleteDialogComponent],
  entryComponents: [TabCoordonneeDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabCoordonneeModule {}
