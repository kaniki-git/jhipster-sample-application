import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUserProfilDetailComponent } from 'app/entities/tab-user-profil/tab-user-profil-detail.component';
import { TabUserProfil } from 'app/shared/model/tab-user-profil.model';

describe('Component Tests', () => {
  describe('TabUserProfil Management Detail Component', () => {
    let comp: TabUserProfilDetailComponent;
    let fixture: ComponentFixture<TabUserProfilDetailComponent>;
    const route = ({ data: of({ tabUserProfil: new TabUserProfil(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUserProfilDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabUserProfilDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabUserProfilDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabUserProfil on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabUserProfil).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
