import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamPhysComponent } from 'app/entities/tab-exam-phys/tab-exam-phys.component';
import { TabExamPhysService } from 'app/entities/tab-exam-phys/tab-exam-phys.service';
import { TabExamPhys } from 'app/shared/model/tab-exam-phys.model';

describe('Component Tests', () => {
  describe('TabExamPhys Management Component', () => {
    let comp: TabExamPhysComponent;
    let fixture: ComponentFixture<TabExamPhysComponent>;
    let service: TabExamPhysService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamPhysComponent],
      })
        .overrideTemplate(TabExamPhysComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabExamPhysComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabExamPhysService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabExamPhys(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabExamPhys && comp.tabExamPhys[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
