import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUserComponent } from 'app/entities/tab-user/tab-user.component';
import { TabUserService } from 'app/entities/tab-user/tab-user.service';
import { TabUser } from 'app/shared/model/tab-user.model';

describe('Component Tests', () => {
  describe('TabUser Management Component', () => {
    let comp: TabUserComponent;
    let fixture: ComponentFixture<TabUserComponent>;
    let service: TabUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUserComponent],
      })
        .overrideTemplate(TabUserComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabUserComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabUserService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabUser(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabUsers && comp.tabUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
