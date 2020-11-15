import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabHistoriquePatientComponent } from 'app/entities/tab-historique-patient/tab-historique-patient.component';
import { TabHistoriquePatientService } from 'app/entities/tab-historique-patient/tab-historique-patient.service';
import { TabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';

describe('Component Tests', () => {
  describe('TabHistoriquePatient Management Component', () => {
    let comp: TabHistoriquePatientComponent;
    let fixture: ComponentFixture<TabHistoriquePatientComponent>;
    let service: TabHistoriquePatientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHistoriquePatientComponent],
      })
        .overrideTemplate(TabHistoriquePatientComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabHistoriquePatientComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabHistoriquePatientService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabHistoriquePatient(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabHistoriquePatients && comp.tabHistoriquePatients[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
