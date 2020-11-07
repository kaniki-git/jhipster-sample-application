import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUrgenceDetailComponent } from 'app/entities/tab-urgence/tab-urgence-detail.component';
import { TabUrgence } from 'app/shared/model/tab-urgence.model';

describe('Component Tests', () => {
  describe('TabUrgence Management Detail Component', () => {
    let comp: TabUrgenceDetailComponent;
    let fixture: ComponentFixture<TabUrgenceDetailComponent>;
    const route = ({ data: of({ tabUrgence: new TabUrgence(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUrgenceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabUrgenceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabUrgenceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabUrgence on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabUrgence).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
