import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabAdministratifComponent } from 'app/entities/tab-administratif/tab-administratif.component';
import { TabAdministratifService } from 'app/entities/tab-administratif/tab-administratif.service';
import { TabAdministratif } from 'app/shared/model/tab-administratif.model';

describe('Component Tests', () => {
  describe('TabAdministratif Management Component', () => {
    let comp: TabAdministratifComponent;
    let fixture: ComponentFixture<TabAdministratifComponent>;
    let service: TabAdministratifService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabAdministratifComponent],
      })
        .overrideTemplate(TabAdministratifComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabAdministratifComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabAdministratifService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabAdministratif(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabAdministratifs && comp.tabAdministratifs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
