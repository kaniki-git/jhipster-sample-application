import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabBiologieComponent } from 'app/entities/tab-biologie/tab-biologie.component';
import { TabBiologieService } from 'app/entities/tab-biologie/tab-biologie.service';
import { TabBiologie } from 'app/shared/model/tab-biologie.model';

describe('Component Tests', () => {
  describe('TabBiologie Management Component', () => {
    let comp: TabBiologieComponent;
    let fixture: ComponentFixture<TabBiologieComponent>;
    let service: TabBiologieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabBiologieComponent],
      })
        .overrideTemplate(TabBiologieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabBiologieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabBiologieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabBiologie(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabBiologies && comp.tabBiologies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
