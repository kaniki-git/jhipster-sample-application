import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamTechComponent } from 'app/entities/tab-exam-tech/tab-exam-tech.component';
import { TabExamTechService } from 'app/entities/tab-exam-tech/tab-exam-tech.service';
import { TabExamTech } from 'app/shared/model/tab-exam-tech.model';

describe('Component Tests', () => {
  describe('TabExamTech Management Component', () => {
    let comp: TabExamTechComponent;
    let fixture: ComponentFixture<TabExamTechComponent>;
    let service: TabExamTechService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamTechComponent],
      })
        .overrideTemplate(TabExamTechComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabExamTechComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabExamTechService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabExamTech(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabExamTeches && comp.tabExamTeches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
