import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabPatientDetailComponent } from 'app/entities/tab-patient/tab-patient-detail.component';
import { TabPatient } from 'app/shared/model/tab-patient.model';

describe('Component Tests', () => {
  describe('TabPatient Management Detail Component', () => {
    let comp: TabPatientDetailComponent;
    let fixture: ComponentFixture<TabPatientDetailComponent>;
    const route = ({ data: of({ tabPatient: new TabPatient(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabPatientDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabPatientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabPatientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabPatient on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabPatient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
