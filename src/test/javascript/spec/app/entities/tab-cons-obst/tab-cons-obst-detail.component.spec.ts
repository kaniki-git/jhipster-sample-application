import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsObstDetailComponent } from 'app/entities/tab-cons-obst/tab-cons-obst-detail.component';
import { TabConsObst } from 'app/shared/model/tab-cons-obst.model';

describe('Component Tests', () => {
  describe('TabConsObst Management Detail Component', () => {
    let comp: TabConsObstDetailComponent;
    let fixture: ComponentFixture<TabConsObstDetailComponent>;
    const route = ({ data: of({ tabConsObst: new TabConsObst(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsObstDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabConsObstDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabConsObstDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabConsObst on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabConsObst).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
