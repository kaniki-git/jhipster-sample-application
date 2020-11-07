import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabGrossesseComponent } from 'app/entities/tab-grossesse/tab-grossesse.component';
import { TabGrossesseService } from 'app/entities/tab-grossesse/tab-grossesse.service';
import { TabGrossesse } from 'app/shared/model/tab-grossesse.model';

describe('Component Tests', () => {
  describe('TabGrossesse Management Component', () => {
    let comp: TabGrossesseComponent;
    let fixture: ComponentFixture<TabGrossesseComponent>;
    let service: TabGrossesseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabGrossesseComponent],
      })
        .overrideTemplate(TabGrossesseComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabGrossesseComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabGrossesseService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabGrossesse(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabGrossesses && comp.tabGrossesses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
