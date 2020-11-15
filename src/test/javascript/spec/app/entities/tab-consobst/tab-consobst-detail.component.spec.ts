import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsobstDetailComponent } from 'app/entities/tab-consobst/tab-consobst-detail.component';
import { TabConsobst } from 'app/shared/model/tab-consobst.model';

describe('Component Tests', () => {
  describe('TabConsobst Management Detail Component', () => {
    let comp: TabConsobstDetailComponent;
    let fixture: ComponentFixture<TabConsobstDetailComponent>;
    const route = ({ data: of({ tabConsobst: new TabConsobst(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsobstDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabConsobstDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabConsobstDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabConsobst on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabConsobst).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
