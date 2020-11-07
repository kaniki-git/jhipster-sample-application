import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabSpecialiteComponent } from 'app/entities/tab-specialite/tab-specialite.component';
import { TabSpecialiteService } from 'app/entities/tab-specialite/tab-specialite.service';
import { TabSpecialite } from 'app/shared/model/tab-specialite.model';

describe('Component Tests', () => {
  describe('TabSpecialite Management Component', () => {
    let comp: TabSpecialiteComponent;
    let fixture: ComponentFixture<TabSpecialiteComponent>;
    let service: TabSpecialiteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabSpecialiteComponent],
      })
        .overrideTemplate(TabSpecialiteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabSpecialiteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabSpecialiteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabSpecialite(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabSpecialites && comp.tabSpecialites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
