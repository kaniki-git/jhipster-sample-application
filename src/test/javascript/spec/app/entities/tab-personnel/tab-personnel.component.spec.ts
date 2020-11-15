import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabPersonnelComponent } from 'app/entities/tab-personnel/tab-personnel.component';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';
import { TabPersonnel } from 'app/shared/model/tab-personnel.model';

describe('Component Tests', () => {
  describe('TabPersonnel Management Component', () => {
    let comp: TabPersonnelComponent;
    let fixture: ComponentFixture<TabPersonnelComponent>;
    let service: TabPersonnelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabPersonnelComponent],
      })
        .overrideTemplate(TabPersonnelComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabPersonnelComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabPersonnelService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabPersonnel(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabPersonnels && comp.tabPersonnels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
