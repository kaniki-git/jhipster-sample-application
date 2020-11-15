import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRefPaysDetailComponent } from 'app/entities/tab-ref-pays/tab-ref-pays-detail.component';
import { TabRefPays } from 'app/shared/model/tab-ref-pays.model';

describe('Component Tests', () => {
  describe('TabRefPays Management Detail Component', () => {
    let comp: TabRefPaysDetailComponent;
    let fixture: ComponentFixture<TabRefPaysDetailComponent>;
    const route = ({ data: of({ tabRefPays: new TabRefPays(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRefPaysDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabRefPaysDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabRefPaysDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabRefPays on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabRefPays).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
