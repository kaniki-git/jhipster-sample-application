import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsobstComponent } from 'app/entities/tab-consobst/tab-consobst.component';
import { TabConsobstService } from 'app/entities/tab-consobst/tab-consobst.service';
import { TabConsobst } from 'app/shared/model/tab-consobst.model';

describe('Component Tests', () => {
  describe('TabConsobst Management Component', () => {
    let comp: TabConsobstComponent;
    let fixture: ComponentFixture<TabConsobstComponent>;
    let service: TabConsobstService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsobstComponent],
      })
        .overrideTemplate(TabConsobstComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabConsobstComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabConsobstService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabConsobst(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabConsobsts && comp.tabConsobsts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
