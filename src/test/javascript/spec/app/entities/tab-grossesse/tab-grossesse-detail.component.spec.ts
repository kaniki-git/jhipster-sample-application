import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabGrossesseDetailComponent } from 'app/entities/tab-grossesse/tab-grossesse-detail.component';
import { TabGrossesse } from 'app/shared/model/tab-grossesse.model';

describe('Component Tests', () => {
  describe('TabGrossesse Management Detail Component', () => {
    let comp: TabGrossesseDetailComponent;
    let fixture: ComponentFixture<TabGrossesseDetailComponent>;
    const route = ({ data: of({ tabGrossesse: new TabGrossesse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabGrossesseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabGrossesseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabGrossesseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabGrossesse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabGrossesse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
