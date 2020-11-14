import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamPhysDetailComponent } from 'app/entities/tab-exam-phys/tab-exam-phys-detail.component';
import { TabExamPhys } from 'app/shared/model/tab-exam-phys.model';

describe('Component Tests', () => {
  describe('TabExamPhys Management Detail Component', () => {
    let comp: TabExamPhysDetailComponent;
    let fixture: ComponentFixture<TabExamPhysDetailComponent>;
    const route = ({ data: of({ tabExamPhys: new TabExamPhys(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamPhysDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabExamPhysDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabExamPhysDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabExamPhys on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabExamPhys).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
