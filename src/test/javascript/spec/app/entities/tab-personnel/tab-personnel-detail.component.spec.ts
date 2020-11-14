import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabPersonnelDetailComponent } from 'app/entities/tab-personnel/tab-personnel-detail.component';
import { TabPersonnel } from 'app/shared/model/tab-personnel.model';

describe('Component Tests', () => {
  describe('TabPersonnel Management Detail Component', () => {
    let comp: TabPersonnelDetailComponent;
    let fixture: ComponentFixture<TabPersonnelDetailComponent>;
    const route = ({ data: of({ tabPersonnel: new TabPersonnel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabPersonnelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabPersonnelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabPersonnelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabPersonnel on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabPersonnel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
