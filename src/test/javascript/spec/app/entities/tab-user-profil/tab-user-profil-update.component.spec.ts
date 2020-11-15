import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUserProfilUpdateComponent } from 'app/entities/tab-user-profil/tab-user-profil-update.component';
import { TabUserProfilService } from 'app/entities/tab-user-profil/tab-user-profil.service';
import { TabUserProfil } from 'app/shared/model/tab-user-profil.model';

describe('Component Tests', () => {
  describe('TabUserProfil Management Update Component', () => {
    let comp: TabUserProfilUpdateComponent;
    let fixture: ComponentFixture<TabUserProfilUpdateComponent>;
    let service: TabUserProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUserProfilUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabUserProfilUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabUserProfilUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabUserProfilService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabUserProfil(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabUserProfil();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
