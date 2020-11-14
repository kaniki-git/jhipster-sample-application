import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabBiologieComponent } from './tab-biologie.component';
import { TabBiologieDetailComponent } from './tab-biologie-detail.component';
import { TabBiologieUpdateComponent } from './tab-biologie-update.component';
import { TabBiologieDeleteDialogComponent } from './tab-biologie-delete-dialog.component';
import { tabBiologieRoute } from './tab-biologie.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabBiologieRoute)],
  declarations: [TabBiologieComponent, TabBiologieDetailComponent, TabBiologieUpdateComponent, TabBiologieDeleteDialogComponent],
  entryComponents: [TabBiologieDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabBiologieModule {}
