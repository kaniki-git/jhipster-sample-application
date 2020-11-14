import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabBiologieDetailComponent } from 'app/entities/tab-biologie/tab-biologie-detail.component';
import { TabBiologie } from 'app/shared/model/tab-biologie.model';

describe('Component Tests', () => {
  describe('TabBiologie Management Detail Component', () => {
    let comp: TabBiologieDetailComponent;
    let fixture: ComponentFixture<TabBiologieDetailComponent>;
    const route = ({ data: of({ tabBiologie: new TabBiologie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabBiologieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabBiologieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabBiologieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabBiologie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabBiologie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
