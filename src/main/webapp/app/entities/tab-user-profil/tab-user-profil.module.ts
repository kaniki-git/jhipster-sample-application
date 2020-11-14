import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabUserProfilComponent } from './tab-user-profil.component';
import { TabUserProfilDetailComponent } from './tab-user-profil-detail.component';
import { TabUserProfilUpdateComponent } from './tab-user-profil-update.component';
import { TabUserProfilDeleteDialogComponent } from './tab-user-profil-delete-dialog.component';
import { tabUserProfilRoute } from './tab-user-profil.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabUserProfilRoute)],
  declarations: [TabUserProfilComponent, TabUserProfilDetailComponent, TabUserProfilUpdateComponent, TabUserProfilDeleteDialogComponent],
  entryComponents: [TabUserProfilDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabUserProfilModule {}
