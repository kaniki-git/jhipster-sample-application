import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabHistoriquePatientUpdateComponent } from 'app/entities/tab-historique-patient/tab-historique-patient-update.component';
import { TabHistoriquePatientService } from 'app/entities/tab-historique-patient/tab-historique-patient.service';
import { TabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';

describe('Component Tests', () => {
  describe('TabHistoriquePatient Management Update Component', () => {
    let comp: TabHistoriquePatientUpdateComponent;
    let fixture: ComponentFixture<TabHistoriquePatientUpdateComponent>;
    let service: TabHistoriquePatientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHistoriquePatientUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabHistoriquePatientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabHistoriquePatientUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabHistoriquePatientService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabHistoriquePatient(123);
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
        const entity = new TabHistoriquePatient();
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
