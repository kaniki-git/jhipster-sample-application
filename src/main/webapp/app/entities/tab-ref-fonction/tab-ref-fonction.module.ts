import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { TabRefFonctionComponent } from './tab-ref-fonction.component';
import { TabRefFonctionDetailComponent } from './tab-ref-fonction-detail.component';
import { TabRefFonctionUpdateComponent } from './tab-ref-fonction-update.component';
import { TabRefFonctionDeleteDialogComponent } from './tab-ref-fonction-delete-dialog.component';
import { tabRefFonctionRoute } from './tab-ref-fonction.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(tabRefFonctionRoute)],
  declarations: [
    TabRefFonctionComponent,
    TabRefFonctionDetailComponent,
    TabRefFonctionUpdateComponent,
    TabRefFonctionDeleteDialogComponent,
  ],
  entryComponents: [TabRefFonctionDeleteDialogComponent],
})
export class JhipsterSampleApplicationTabRefFonctionModule {}
