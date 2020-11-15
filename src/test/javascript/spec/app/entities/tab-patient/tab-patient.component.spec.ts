import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabPatientComponent } from 'app/entities/tab-patient/tab-patient.component';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';
import { TabPatient } from 'app/shared/model/tab-patient.model';

describe('Component Tests', () => {
  describe('TabPatient Management Component', () => {
    let comp: TabPatientComponent;
    let fixture: ComponentFixture<TabPatientComponent>;
    let service: TabPatientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabPatientComponent],
      })
        .overrideTemplate(TabPatientComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabPatientComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabPatientService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabPatient(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabPatients && comp.tabPatients[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
