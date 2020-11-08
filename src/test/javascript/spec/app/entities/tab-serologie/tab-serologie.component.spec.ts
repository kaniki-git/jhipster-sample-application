import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabSerologieComponent } from 'app/entities/tab-serologie/tab-serologie.component';
import { TabSerologieService } from 'app/entities/tab-serologie/tab-serologie.service';
import { TabSerologie } from 'app/shared/model/tab-serologie.model';

describe('Component Tests', () => {
  describe('TabSerologie Management Component', () => {
    let comp: TabSerologieComponent;
    let fixture: ComponentFixture<TabSerologieComponent>;
    let service: TabSerologieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabSerologieComponent],
      })
        .overrideTemplate(TabSerologieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabSerologieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabSerologieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabSerologie(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabSerologies && comp.tabSerologies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
