import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabParamvitauxComponent } from 'app/entities/tab-paramvitaux/tab-paramvitaux.component';
import { TabParamvitauxService } from 'app/entities/tab-paramvitaux/tab-paramvitaux.service';
import { TabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';

describe('Component Tests', () => {
  describe('TabParamvitaux Management Component', () => {
    let comp: TabParamvitauxComponent;
    let fixture: ComponentFixture<TabParamvitauxComponent>;
    let service: TabParamvitauxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabParamvitauxComponent],
      })
        .overrideTemplate(TabParamvitauxComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabParamvitauxComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabParamvitauxService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabParamvitaux(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabParamvitauxes && comp.tabParamvitauxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
