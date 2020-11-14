import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabHospitalUpdateComponent } from 'app/entities/tab-hospital/tab-hospital-update.component';
import { TabHospitalService } from 'app/entities/tab-hospital/tab-hospital.service';
import { TabHospital } from 'app/shared/model/tab-hospital.model';

describe('Component Tests', () => {
  describe('TabHospital Management Update Component', () => {
    let comp: TabHospitalUpdateComponent;
    let fixture: ComponentFixture<TabHospitalUpdateComponent>;
    let service: TabHospitalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHospitalUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabHospitalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabHospitalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabHospitalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabHospital(123);
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
        const entity = new TabHospital();
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
