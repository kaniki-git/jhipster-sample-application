import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabParamVitauxComponent } from 'app/entities/tab-param-vitaux/tab-param-vitaux.component';
import { TabParamVitauxService } from 'app/entities/tab-param-vitaux/tab-param-vitaux.service';
import { TabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';

describe('Component Tests', () => {
  describe('TabParamVitaux Management Component', () => {
    let comp: TabParamVitauxComponent;
    let fixture: ComponentFixture<TabParamVitauxComponent>;
    let service: TabParamVitauxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabParamVitauxComponent],
      })
        .overrideTemplate(TabParamVitauxComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabParamVitauxComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabParamVitauxService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabParamVitaux(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabParamVitauxes && comp.tabParamVitauxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
