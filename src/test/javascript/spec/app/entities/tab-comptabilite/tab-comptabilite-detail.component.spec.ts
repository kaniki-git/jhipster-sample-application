import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabComptabiliteDetailComponent } from 'app/entities/tab-comptabilite/tab-comptabilite-detail.component';
import { TabComptabilite } from 'app/shared/model/tab-comptabilite.model';

describe('Component Tests', () => {
  describe('TabComptabilite Management Detail Component', () => {
    let comp: TabComptabiliteDetailComponent;
    let fixture: ComponentFixture<TabComptabiliteDetailComponent>;
    const route = ({ data: of({ tabComptabilite: new TabComptabilite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabComptabiliteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabComptabiliteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabComptabiliteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabComptabilite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabComptabilite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
