import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamphysDetailComponent } from 'app/entities/tab-examphys/tab-examphys-detail.component';
import { TabExamphys } from 'app/shared/model/tab-examphys.model';

describe('Component Tests', () => {
  describe('TabExamphys Management Detail Component', () => {
    let comp: TabExamphysDetailComponent;
    let fixture: ComponentFixture<TabExamphysDetailComponent>;
    const route = ({ data: of({ tabExamphys: new TabExamphys(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamphysDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabExamphysDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabExamphysDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabExamphys on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabExamphys).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
