import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRendezvousComponent } from 'app/entities/tab-rendezvous/tab-rendezvous.component';
import { TabRendezvousService } from 'app/entities/tab-rendezvous/tab-rendezvous.service';
import { TabRendezvous } from 'app/shared/model/tab-rendezvous.model';

describe('Component Tests', () => {
  describe('TabRendezvous Management Component', () => {
    let comp: TabRendezvousComponent;
    let fixture: ComponentFixture<TabRendezvousComponent>;
    let service: TabRendezvousService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRendezvousComponent],
      })
        .overrideTemplate(TabRendezvousComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabRendezvousComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabRendezvousService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabRendezvous(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabRendezvous && comp.tabRendezvous[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
