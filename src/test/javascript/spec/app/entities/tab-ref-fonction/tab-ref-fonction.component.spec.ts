import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRefFonctionComponent } from 'app/entities/tab-ref-fonction/tab-ref-fonction.component';
import { TabRefFonctionService } from 'app/entities/tab-ref-fonction/tab-ref-fonction.service';
import { TabRefFonction } from 'app/shared/model/tab-ref-fonction.model';

describe('Component Tests', () => {
  describe('TabRefFonction Management Component', () => {
    let comp: TabRefFonctionComponent;
    let fixture: ComponentFixture<TabRefFonctionComponent>;
    let service: TabRefFonctionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRefFonctionComponent],
      })
        .overrideTemplate(TabRefFonctionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabRefFonctionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabRefFonctionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabRefFonction(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabRefFonctions && comp.tabRefFonctions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
