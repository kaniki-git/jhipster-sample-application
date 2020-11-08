import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRendezvousDetailComponent } from 'app/entities/tab-rendezvous/tab-rendezvous-detail.component';
import { TabRendezvous } from 'app/shared/model/tab-rendezvous.model';

describe('Component Tests', () => {
  describe('TabRendezvous Management Detail Component', () => {
    let comp: TabRendezvousDetailComponent;
    let fixture: ComponentFixture<TabRendezvousDetailComponent>;
    const route = ({ data: of({ tabRendezvous: new TabRendezvous(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRendezvousDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabRendezvousDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabRendezvousDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabRendezvous on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabRendezvous).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
