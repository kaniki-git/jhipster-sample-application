import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabAdministratifDetailComponent } from 'app/entities/tab-administratif/tab-administratif-detail.component';
import { TabAdministratif } from 'app/shared/model/tab-administratif.model';

describe('Component Tests', () => {
  describe('TabAdministratif Management Detail Component', () => {
    let comp: TabAdministratifDetailComponent;
    let fixture: ComponentFixture<TabAdministratifDetailComponent>;
    const route = ({ data: of({ tabAdministratif: new TabAdministratif(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabAdministratifDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabAdministratifDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabAdministratifDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabAdministratif on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabAdministratif).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
