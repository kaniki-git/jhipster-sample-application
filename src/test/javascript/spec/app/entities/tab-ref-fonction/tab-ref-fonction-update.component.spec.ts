import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRefFonctionUpdateComponent } from 'app/entities/tab-ref-fonction/tab-ref-fonction-update.component';
import { TabRefFonctionService } from 'app/entities/tab-ref-fonction/tab-ref-fonction.service';
import { TabRefFonction } from 'app/shared/model/tab-ref-fonction.model';

describe('Component Tests', () => {
  describe('TabRefFonction Management Update Component', () => {
    let comp: TabRefFonctionUpdateComponent;
    let fixture: ComponentFixture<TabRefFonctionUpdateComponent>;
    let service: TabRefFonctionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRefFonctionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabRefFonctionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabRefFonctionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabRefFonctionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabRefFonction(123);
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
        const entity = new TabRefFonction();
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
