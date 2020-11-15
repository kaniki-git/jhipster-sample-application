import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabGynecologieDetailComponent } from 'app/entities/tab-gynecologie/tab-gynecologie-detail.component';
import { TabGynecologie } from 'app/shared/model/tab-gynecologie.model';

describe('Component Tests', () => {
  describe('TabGynecologie Management Detail Component', () => {
    let comp: TabGynecologieDetailComponent;
    let fixture: ComponentFixture<TabGynecologieDetailComponent>;
    const route = ({ data: of({ tabGynecologie: new TabGynecologie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabGynecologieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabGynecologieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabGynecologieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabGynecologie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabGynecologie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
