import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabAccouchementDetailComponent } from 'app/entities/tab-accouchement/tab-accouchement-detail.component';
import { TabAccouchement } from 'app/shared/model/tab-accouchement.model';

describe('Component Tests', () => {
  describe('TabAccouchement Management Detail Component', () => {
    let comp: TabAccouchementDetailComponent;
    let fixture: ComponentFixture<TabAccouchementDetailComponent>;
    const route = ({ data: of({ tabAccouchement: new TabAccouchement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabAccouchementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabAccouchementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabAccouchementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabAccouchement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabAccouchement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
