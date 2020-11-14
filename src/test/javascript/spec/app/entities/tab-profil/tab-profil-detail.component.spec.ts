import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabProfilDetailComponent } from 'app/entities/tab-profil/tab-profil-detail.component';
import { TabProfil } from 'app/shared/model/tab-profil.model';

describe('Component Tests', () => {
  describe('TabProfil Management Detail Component', () => {
    let comp: TabProfilDetailComponent;
    let fixture: ComponentFixture<TabProfilDetailComponent>;
    const route = ({ data: of({ tabProfil: new TabProfil(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabProfilDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabProfilDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabProfilDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabProfil on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabProfil).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
