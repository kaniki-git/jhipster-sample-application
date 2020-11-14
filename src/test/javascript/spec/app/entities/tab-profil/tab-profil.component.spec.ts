import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabProfilComponent } from 'app/entities/tab-profil/tab-profil.component';
import { TabProfilService } from 'app/entities/tab-profil/tab-profil.service';
import { TabProfil } from 'app/shared/model/tab-profil.model';

describe('Component Tests', () => {
  describe('TabProfil Management Component', () => {
    let comp: TabProfilComponent;
    let fixture: ComponentFixture<TabProfilComponent>;
    let service: TabProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabProfilComponent],
      })
        .overrideTemplate(TabProfilComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabProfilComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabProfilService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TabProfil(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tabProfils && comp.tabProfils[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
