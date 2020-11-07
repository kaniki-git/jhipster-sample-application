import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUserProfilComponent } from 'app/entities/tab-user-profil/tab-user-profil.component';
import { TabUserProfilService } from 'app/entities/tab-user-profil/tab-user-profil.service';
import { TabUserProfil } from 'app/shared/model/tab-user-profil.model';

describe('Component Tests', () => {
  describe('TabUserProfil Management Component', () => {
    let comp: TabUserProfilComponent;
    let fixture: ComponentFixture<TabUserProfilComponent>;
    let service: TabUserProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUserProfilComponent],
      })
        .overrideTemplate(TabUserProfilComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabUserProfilComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabUserProfilService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabUserProfil(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabUserProfils && comp.tabUserProfils[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
