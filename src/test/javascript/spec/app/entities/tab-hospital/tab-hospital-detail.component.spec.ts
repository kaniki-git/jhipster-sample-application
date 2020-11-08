import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabHospitalDetailComponent } from 'app/entities/tab-hospital/tab-hospital-detail.component';
import { TabHospital } from 'app/shared/model/tab-hospital.model';

describe('Component Tests', () => {
  describe('TabHospital Management Detail Component', () => {
    let comp: TabHospitalDetailComponent;
    let fixture: ComponentFixture<TabHospitalDetailComponent>;
    const route = ({ data: of({ tabHospital: new TabHospital(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHospitalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabHospitalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabHospitalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabHospital on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabHospital).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
