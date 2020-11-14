import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamPhysUpdateComponent } from 'app/entities/tab-exam-phys/tab-exam-phys-update.component';
import { TabExamPhysService } from 'app/entities/tab-exam-phys/tab-exam-phys.service';
import { TabExamPhys } from 'app/shared/model/tab-exam-phys.model';

describe('Component Tests', () => {
  describe('TabExamPhys Management Update Component', () => {
    let comp: TabExamPhysUpdateComponent;
    let fixture: ComponentFixture<TabExamPhysUpdateComponent>;
    let service: TabExamPhysService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamPhysUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabExamPhysUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabExamPhysUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabExamPhysService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabExamPhys(123);
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
        const entity = new TabExamPhys();
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
