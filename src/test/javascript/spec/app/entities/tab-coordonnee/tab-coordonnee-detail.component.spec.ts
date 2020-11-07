import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabCoordonneeDetailComponent } from 'app/entities/tab-coordonnee/tab-coordonnee-detail.component';
import { TabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

describe('Component Tests', () => {
  describe('TabCoordonnee Management Detail Component', () => {
    let comp: TabCoordonneeDetailComponent;
    let fixture: ComponentFixture<TabCoordonneeDetailComponent>;
    const route = ({ data: of({ tabCoordonnee: new TabCoordonnee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabCoordonneeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabCoordonneeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabCoordonneeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabCoordonnee on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabCoordonnee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
