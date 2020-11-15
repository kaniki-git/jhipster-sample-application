import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabHospitalComponent } from 'app/entities/tab-hospital/tab-hospital.component';
import { TabHospitalService } from 'app/entities/tab-hospital/tab-hospital.service';
import { TabHospital } from 'app/shared/model/tab-hospital.model';

describe('Component Tests', () => {
  describe('TabHospital Management Component', () => {
    let comp: TabHospitalComponent;
    let fixture: ComponentFixture<TabHospitalComponent>;
    let service: TabHospitalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHospitalComponent],
      })
        .overrideTemplate(TabHospitalComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabHospitalComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabHospitalService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabHospital(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabHospitals && comp.tabHospitals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
