import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabGynecologieComponent } from './tab-gynecologie.component';
import { TabGynecologieDetailComponent } from './tab-gynecologie-detail.component';
import { TabGynecologieUpdateComponent } from './tab-gynecologie-update.component';
import { TabGynecologieDeleteDialogComponent } from './tab-gynecologie-delete-dialog.component';
import { tabGynecologieRoute } from './tab-gynecologie.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabGynecologieRoute)],
  declarations: [
    TabGynecologieComponent,
    TabGynecologieDetailComponent,
    TabGynecologieUpdateComponent,
    TabGynecologieDeleteDialogComponent,
  ],
  entryComponents: [TabGynecologieDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabGynecologieModule {}
