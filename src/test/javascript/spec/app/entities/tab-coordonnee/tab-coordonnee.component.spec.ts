import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabCoordonneeComponent } from 'app/entities/tab-coordonnee/tab-coordonnee.component';
import { TabCoordonneeService } from 'app/entities/tab-coordonnee/tab-coordonnee.service';
import { TabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

describe('Component Tests', () => {
  describe('TabCoordonnee Management Component', () => {
    let comp: TabCoordonneeComponent;
    let fixture: ComponentFixture<TabCoordonneeComponent>;
    let service: TabCoordonneeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabCoordonneeComponent],
      })
        .overrideTemplate(TabCoordonneeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabCoordonneeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabCoordonneeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabCoordonnee(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabCoordonnees && comp.tabCoordonnees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
