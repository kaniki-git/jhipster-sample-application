import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabParamvitauxUpdateComponent } from 'app/entities/tab-paramvitaux/tab-paramvitaux-update.component';
import { TabParamvitauxService } from 'app/entities/tab-paramvitaux/tab-paramvitaux.service';
import { TabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';

describe('Component Tests', () => {
  describe('TabParamvitaux Management Update Component', () => {
    let comp: TabParamvitauxUpdateComponent;
    let fixture: ComponentFixture<TabParamvitauxUpdateComponent>;
    let service: TabParamvitauxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabParamvitauxUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabParamvitauxUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabParamvitauxUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabParamvitauxService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabParamvitaux(123);
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
        const entity = new TabParamvitaux();
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
