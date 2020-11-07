import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabParamVitauxComponent } from './tab-param-vitaux.component';
import { TabParamVitauxDetailComponent } from './tab-param-vitaux-detail.component';
import { TabParamVitauxUpdateComponent } from './tab-param-vitaux-update.component';
import { TabParamVitauxDeleteDialogComponent } from './tab-param-vitaux-delete-dialog.component';
import { tabParamVitauxRoute } from './tab-param-vitaux.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabParamVitauxRoute)],
  declarations: [
    TabParamVitauxComponent,
    TabParamVitauxDetailComponent,
    TabParamVitauxUpdateComponent,
    TabParamVitauxDeleteDialogComponent,
  ],
  entryComponents: [TabParamVitauxDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabParamVitauxModule {}
