import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabPatientUpdateComponent } from 'app/entities/tab-patient/tab-patient-update.component';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';
import { TabPatient } from 'app/shared/model/tab-patient.model';

describe('Component Tests', () => {
  describe('TabPatient Management Update Component', () => {
    let comp: TabPatientUpdateComponent;
    let fixture: ComponentFixture<TabPatientUpdateComponent>;
    let service: TabPatientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabPatientUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabPatientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabPatientUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabPatientService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabPatient(123);
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
        const entity = new TabPatient();
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
