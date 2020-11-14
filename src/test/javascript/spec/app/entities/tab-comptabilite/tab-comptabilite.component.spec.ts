import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabComptabiliteComponent } from 'app/entities/tab-comptabilite/tab-comptabilite.component';
import { TabComptabiliteService } from 'app/entities/tab-comptabilite/tab-comptabilite.service';
import { TabComptabilite } from 'app/shared/model/tab-comptabilite.model';

describe('Component Tests', () => {
  describe('TabComptabilite Management Component', () => {
    let comp: TabComptabiliteComponent;
    let fixture: ComponentFixture<TabComptabiliteComponent>;
    let service: TabComptabiliteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabComptabiliteComponent],
      })
        .overrideTemplate(TabComptabiliteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabComptabiliteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabComptabiliteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabComptabilite(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabComptabilites && comp.tabComptabilites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
