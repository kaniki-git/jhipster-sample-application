import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRefPaysComponent } from 'app/entities/tab-ref-pays/tab-ref-pays.component';
import { TabRefPaysService } from 'app/entities/tab-ref-pays/tab-ref-pays.service';
import { TabRefPays } from 'app/shared/model/tab-ref-pays.model';

describe('Component Tests', () => {
  describe('TabRefPays Management Component', () => {
    let comp: TabRefPaysComponent;
    let fixture: ComponentFixture<TabRefPaysComponent>;
    let service: TabRefPaysService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRefPaysComponent],
      })
        .overrideTemplate(TabRefPaysComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabRefPaysComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabRefPaysService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabRefPays(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabRefPays && comp.tabRefPays[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
