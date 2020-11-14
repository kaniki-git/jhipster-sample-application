import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tab-patient',
        loadChildren: () => import('./tab-patient/tab-patient.module').then(m => m.JhipsterSampleApplicationTabPatientModule),
      },
      {
        path: 'tab-administratif',
        loadChildren: () =>
          import('./tab-administratif/tab-administratif.module').then(m => m.JhipsterSampleApplicationTabAdministratifModule),
      },
      {
        path: 'tab-coordonnee',
        loadChildren: () => import('./tab-coordonnee/tab-coordonnee.module').then(m => m.JhipsterSampleApplicationTabCoordonneeModule),
      },
      {
        path: 'tab-personnel',
        loadChildren: () => import('./tab-personnel/tab-personnel.module').then(m => m.JhipsterSampleApplicationTabPersonnelModule),
      },
      {
        path: 'tab-user',
        loadChildren: () => import('./tab-user/tab-user.module').then(m => m.JhipsterSampleApplicationTabUserModule),
      },
      {
        path: 'tab-rendezvous',
        loadChildren: () => import('./tab-rendezvous/tab-rendezvous.module').then(m => m.JhipsterSampleApplicationTabRendezvousModule),
      },
      {
        path: 'tab-specialite',
        loadChildren: () => import('./tab-specialite/tab-specialite.module').then(m => m.JhipsterSampleApplicationTabSpecialiteModule),
      },
      {
        path: 'tab-historique-patient',
        loadChildren: () =>
          import('./tab-historique-patient/tab-historique-patient.module').then(m => m.JhipsterSampleApplicationTabHistoriquePatientModule),
      },
      {
        path: 'tab-profil',
        loadChildren: () => import('./tab-profil/tab-profil.module').then(m => m.JhipsterSampleApplicationTabProfilModule),
      },
      {
        path: 'tab-user-profil',
        loadChildren: () => import('./tab-user-profil/tab-user-profil.module').then(m => m.JhipsterSampleApplicationTabUserProfilModule),
      },
      {
        path: 'tab-consultation',
        loadChildren: () =>
          import('./tab-consultation/tab-consultation.module').then(m => m.JhipsterSampleApplicationTabConsultationModule),
      },
      {
        path: 'tab-param-vitaux',
        loadChildren: () => import('./tab-param-vitaux/tab-param-vitaux.module').then(m => m.JhipsterSampleApplicationTabParamVitauxModule),
      },
      {
        path: 'tab-biologie',
        loadChildren: () => import('./tab-biologie/tab-biologie.module').then(m => m.JhipsterSampleApplicationTabBiologieModule),
      },
      {
        path: 'tab-cons-obst',
        loadChildren: () => import('./tab-cons-obst/tab-cons-obst.module').then(m => m.JhipsterSampleApplicationTabConsObstModule),
      },
      {
        path: 'tab-urgence',
        loadChildren: () => import('./tab-urgence/tab-urgence.module').then(m => m.JhipsterSampleApplicationTabUrgenceModule),
      },
      {
        path: 'tab-hospital',
        loadChildren: () => import('./tab-hospital/tab-hospital.module').then(m => m.JhipsterSampleApplicationTabHospitalModule),
      },
      {
        path: 'tab-gynecologie',
        loadChildren: () => import('./tab-gynecologie/tab-gynecologie.module').then(m => m.JhipsterSampleApplicationTabGynecologieModule),
      },
      {
        path: 'tab-accouchement',
        loadChildren: () =>
          import('./tab-accouchement/tab-accouchement.module').then(m => m.JhipsterSampleApplicationTabAccouchementModule),
      },
      {
        path: 'tab-exam-phys',
        loadChildren: () => import('./tab-exam-phys/tab-exam-phys.module').then(m => m.JhipsterSampleApplicationTabExamPhysModule),
      },
      {
        path: 'tab-comptabilite',
        loadChildren: () =>
          import('./tab-comptabilite/tab-comptabilite.module').then(m => m.JhipsterSampleApplicationTabComptabiliteModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}
