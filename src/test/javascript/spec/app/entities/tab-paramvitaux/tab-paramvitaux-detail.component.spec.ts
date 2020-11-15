import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabParamvitauxDetailComponent } from 'app/entities/tab-paramvitaux/tab-paramvitaux-detail.component';
import { TabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';

describe('Component Tests', () => {
  describe('TabParamvitaux Management Detail Component', () => {
    let comp: TabParamvitauxDetailComponent;
    let fixture: ComponentFixture<TabParamvitauxDetailComponent>;
    const route = ({ data: of({ tabParamvitaux: new TabParamvitaux(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabParamvitauxDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabParamvitauxDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabParamvitauxDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabParamvitaux on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabParamvitaux).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
