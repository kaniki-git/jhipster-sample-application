import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabComptabiliteComponent } from './tab-comptabilite.component';
import { TabComptabiliteDetailComponent } from './tab-comptabilite-detail.component';
import { TabComptabiliteUpdateComponent } from './tab-comptabilite-update.component';
import { TabComptabiliteDeleteDialogComponent } from './tab-comptabilite-delete-dialog.component';
import { tabComptabiliteRoute } from './tab-comptabilite.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabComptabiliteRoute)],
  declarations: [
    TabComptabiliteComponent,
    TabComptabiliteDetailComponent,
    TabComptabiliteUpdateComponent,
    TabComptabiliteDeleteDialogComponent,
  ],
  entryComponents: [TabComptabiliteDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabComptabiliteModule {}
