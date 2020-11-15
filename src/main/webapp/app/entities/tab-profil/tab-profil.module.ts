import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabProfilComponent } from './tab-profil.component';
import { TabProfilDetailComponent } from './tab-profil-detail.component';
import { TabProfilUpdateComponent } from './tab-profil-update.component';
import { TabProfilDeleteDialogComponent } from './tab-profil-delete-dialog.component';
import { tabProfilRoute } from './tab-profil.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabProfilRoute)],
  declarations: [TabProfilComponent, TabProfilDetailComponent, TabProfilUpdateComponent, TabProfilDeleteDialogComponent],
  entryComponents: [TabProfilDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabProfilModule {}
