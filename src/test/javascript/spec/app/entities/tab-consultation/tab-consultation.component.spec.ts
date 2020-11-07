import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsultationComponent } from 'app/entities/tab-consultation/tab-consultation.component';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';
import { TabConsultation } from 'app/shared/model/tab-consultation.model';

describe('Component Tests', () => {
  describe('TabConsultation Management Component', () => {
    let comp: TabConsultationComponent;
    let fixture: ComponentFixture<TabConsultationComponent>;
    let service: TabConsultationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsultationComponent],
      })
        .overrideTemplate(TabConsultationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabConsultationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabConsultationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabConsultation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabConsultations && comp.tabConsultations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
