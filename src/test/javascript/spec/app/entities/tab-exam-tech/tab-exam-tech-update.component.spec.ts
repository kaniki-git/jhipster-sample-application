import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamTechUpdateComponent } from 'app/entities/tab-exam-tech/tab-exam-tech-update.component';
import { TabExamTechService } from 'app/entities/tab-exam-tech/tab-exam-tech.service';
import { TabExamTech } from 'app/shared/model/tab-exam-tech.model';

describe('Component Tests', () => {
  describe('TabExamTech Management Update Component', () => {
    let comp: TabExamTechUpdateComponent;
    let fixture: ComponentFixture<TabExamTechUpdateComponent>;
    let service: TabExamTechService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamTechUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabExamTechUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabExamTechUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabExamTechService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabExamTech(123);
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
        const entity = new TabExamTech();
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
