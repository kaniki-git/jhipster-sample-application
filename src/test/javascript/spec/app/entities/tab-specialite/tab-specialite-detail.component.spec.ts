import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabSpecialiteDetailComponent } from 'app/entities/tab-specialite/tab-specialite-detail.component';
import { TabSpecialite } from 'app/shared/model/tab-specialite.model';

describe('Component Tests', () => {
  describe('TabSpecialite Management Detail Component', () => {
    let comp: TabSpecialiteDetailComponent;
    let fixture: ComponentFixture<TabSpecialiteDetailComponent>;
    const route = ({ data: of({ tabSpecialite: new TabSpecialite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabSpecialiteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabSpecialiteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabSpecialiteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabSpecialite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabSpecialite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
