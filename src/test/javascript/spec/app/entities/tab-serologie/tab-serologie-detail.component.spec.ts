import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabSerologieDetailComponent } from 'app/entities/tab-serologie/tab-serologie-detail.component';
import { TabSerologie } from 'app/shared/model/tab-serologie.model';

describe('Component Tests', () => {
  describe('TabSerologie Management Detail Component', () => {
    let comp: TabSerologieDetailComponent;
    let fixture: ComponentFixture<TabSerologieDetailComponent>;
    const route = ({ data: of({ tabSerologie: new TabSerologie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabSerologieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabSerologieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabSerologieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabSerologie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabSerologie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
