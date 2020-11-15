import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRefFonctionDetailComponent } from 'app/entities/tab-ref-fonction/tab-ref-fonction-detail.component';
import { TabRefFonction } from 'app/shared/model/tab-ref-fonction.model';

describe('Component Tests', () => {
  describe('TabRefFonction Management Detail Component', () => {
    let comp: TabRefFonctionDetailComponent;
    let fixture: ComponentFixture<TabRefFonctionDetailComponent>;
    const route = ({ data: of({ tabRefFonction: new TabRefFonction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRefFonctionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabRefFonctionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabRefFonctionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabRefFonction on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabRefFonction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
