import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsObstComponent } from 'app/entities/tab-cons-obst/tab-cons-obst.component';
import { TabConsObstService } from 'app/entities/tab-cons-obst/tab-cons-obst.service';
import { TabConsObst } from 'app/shared/model/tab-cons-obst.model';

describe('Component Tests', () => {
  describe('TabConsObst Management Component', () => {
    let comp: TabConsObstComponent;
    let fixture: ComponentFixture<TabConsObstComponent>;
    let service: TabConsObstService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsObstComponent],
      })
        .overrideTemplate(TabConsObstComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabConsObstComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabConsObstService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabConsObst(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabConsObsts && comp.tabConsObsts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
