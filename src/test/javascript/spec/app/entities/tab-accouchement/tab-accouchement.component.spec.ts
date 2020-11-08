import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabAccouchementComponent } from 'app/entities/tab-accouchement/tab-accouchement.component';
import { TabAccouchementService } from 'app/entities/tab-accouchement/tab-accouchement.service';
import { TabAccouchement } from 'app/shared/model/tab-accouchement.model';

describe('Component Tests', () => {
  describe('TabAccouchement Management Component', () => {
    let comp: TabAccouchementComponent;
    let fixture: ComponentFixture<TabAccouchementComponent>;
    let service: TabAccouchementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabAccouchementComponent],
      })
        .overrideTemplate(TabAccouchementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabAccouchementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabAccouchementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabAccouchement(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabAccouchements && comp.tabAccouchements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
