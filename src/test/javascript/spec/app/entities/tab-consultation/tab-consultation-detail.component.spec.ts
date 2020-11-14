import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsultationDetailComponent } from 'app/entities/tab-consultation/tab-consultation-detail.component';
import { TabConsultation } from 'app/shared/model/tab-consultation.model';

describe('Component Tests', () => {
  describe('TabConsultation Management Detail Component', () => {
    let comp: TabConsultationDetailComponent;
    let fixture: ComponentFixture<TabConsultationDetailComponent>;
    const route = ({ data: of({ tabConsultation: new TabConsultation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsultationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabConsultationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabConsultationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabConsultation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabConsultation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
