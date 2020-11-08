import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabParamVitauxDetailComponent } from 'app/entities/tab-param-vitaux/tab-param-vitaux-detail.component';
import { TabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';

describe('Component Tests', () => {
  describe('TabParamVitaux Management Detail Component', () => {
    let comp: TabParamVitauxDetailComponent;
    let fixture: ComponentFixture<TabParamVitauxDetailComponent>;
    const route = ({ data: of({ tabParamVitaux: new TabParamVitaux(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabParamVitauxDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabParamVitauxDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabParamVitauxDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabParamVitaux on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabParamVitaux).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
