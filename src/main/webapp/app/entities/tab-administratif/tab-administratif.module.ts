import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabAdministratifComponent } from './tab-administratif.component';
import { TabAdministratifDetailComponent } from './tab-administratif-detail.component';
import { TabAdministratifUpdateComponent } from './tab-administratif-update.component';
import { TabAdministratifDeleteDialogComponent } from './tab-administratif-delete-dialog.component';
import { tabAdministratifRoute } from './tab-administratif.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabAdministratifRoute)],
  declarations: [
    TabAdministratifComponent,
    TabAdministratifDetailComponent,
    TabAdministratifUpdateComponent,
    TabAdministratifDeleteDialogComponent,
  ],
  entryComponents: [TabAdministratifDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabAdministratifModule {}
