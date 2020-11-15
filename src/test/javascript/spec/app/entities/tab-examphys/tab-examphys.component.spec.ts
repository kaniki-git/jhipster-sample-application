import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamphysComponent } from 'app/entities/tab-examphys/tab-examphys.component';
import { TabExamphysService } from 'app/entities/tab-examphys/tab-examphys.service';
import { TabExamphys } from 'app/shared/model/tab-examphys.model';

describe('Component Tests', () => {
  describe('TabExamphys Management Component', () => {
    let comp: TabExamphysComponent;
    let fixture: ComponentFixture<TabExamphysComponent>;
    let service: TabExamphysService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamphysComponent],
      })
        .overrideTemplate(TabExamphysComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabExamphysComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabExamphysService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabExamphys(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabExamphys && comp.tabExamphys[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
