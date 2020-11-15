import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabProfilUpdateComponent } from 'app/entities/tab-profil/tab-profil-update.component';
import { TabProfilService } from 'app/entities/tab-profil/tab-profil.service';
import { TabProfil } from 'app/shared/model/tab-profil.model';

describe('Component Tests', () => {
  describe('TabProfil Management Update Component', () => {
    let comp: TabProfilUpdateComponent;
    let fixture: ComponentFixture<TabProfilUpdateComponent>;
    let service: TabProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabProfilUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabProfilUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabProfilUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabProfilService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabProfil(123);
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
        const entity = new TabProfil();
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
