import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabHistoriquePatientDetailComponent } from 'app/entities/tab-historique-patient/tab-historique-patient-detail.component';
import { TabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';

describe('Component Tests', () => {
  describe('TabHistoriquePatient Management Detail Component', () => {
    let comp: TabHistoriquePatientDetailComponent;
    let fixture: ComponentFixture<TabHistoriquePatientDetailComponent>;
    const route = ({ data: of({ tabHistoriquePatient: new TabHistoriquePatient(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHistoriquePatientDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabHistoriquePatientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabHistoriquePatientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabHistoriquePatient on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabHistoriquePatient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
