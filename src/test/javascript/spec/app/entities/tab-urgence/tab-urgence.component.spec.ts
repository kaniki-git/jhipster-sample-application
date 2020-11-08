import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUrgenceComponent } from 'app/entities/tab-urgence/tab-urgence.component';
import { TabUrgenceService } from 'app/entities/tab-urgence/tab-urgence.service';
import { TabUrgence } from 'app/shared/model/tab-urgence.model';

describe('Component Tests', () => {
  describe('TabUrgence Management Component', () => {
    let comp: TabUrgenceComponent;
    let fixture: ComponentFixture<TabUrgenceComponent>;
    let service: TabUrgenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUrgenceComponent],
      })
        .overrideTemplate(TabUrgenceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabUrgenceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabUrgenceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabUrgence(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabUrgences && comp.tabUrgences[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
