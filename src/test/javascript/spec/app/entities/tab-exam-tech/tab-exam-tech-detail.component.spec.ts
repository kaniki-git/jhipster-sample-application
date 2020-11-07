import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamTechDetailComponent } from 'app/entities/tab-exam-tech/tab-exam-tech-detail.component';
import { TabExamTech } from 'app/shared/model/tab-exam-tech.model';

describe('Component Tests', () => {
  describe('TabExamTech Management Detail Component', () => {
    let comp: TabExamTechDetailComponent;
    let fixture: ComponentFixture<TabExamTechDetailComponent>;
    const route = ({ data: of({ tabExamTech: new TabExamTech(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamTechDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabExamTechDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabExamTechDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabExamTech on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabExamTech).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
