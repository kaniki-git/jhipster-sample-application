import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabGynecologieComponent } from 'app/entities/tab-gynecologie/tab-gynecologie.component';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';
import { TabGynecologie } from 'app/shared/model/tab-gynecologie.model';

describe('Component Tests', () => {
  describe('TabGynecologie Management Component', () => {
    let comp: TabGynecologieComponent;
    let fixture: ComponentFixture<TabGynecologieComponent>;
    let service: TabGynecologieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabGynecologieComponent],
      })
        .overrideTemplate(TabGynecologieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabGynecologieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabGynecologieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabGynecologie(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabGynecologies && comp.tabGynecologies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
