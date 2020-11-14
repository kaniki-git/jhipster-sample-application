import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabComptabiliteUpdateComponent } from 'app/entities/tab-comptabilite/tab-comptabilite-update.component';
import { TabComptabiliteService } from 'app/entities/tab-comptabilite/tab-comptabilite.service';
import { TabComptabilite } from 'app/shared/model/tab-comptabilite.model';

describe('Component Tests', () => {
  describe('TabComptabilite Management Update Component', () => {
    let comp: TabComptabiliteUpdateComponent;
    let fixture: ComponentFixture<TabComptabiliteUpdateComponent>;
    let service: TabComptabiliteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabComptabiliteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabComptabiliteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabComptabiliteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabComptabiliteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabComptabilite(123);
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
        const entity = new TabComptabilite();
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
