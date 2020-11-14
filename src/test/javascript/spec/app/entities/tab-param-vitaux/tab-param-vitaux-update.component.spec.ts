import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabParamVitauxUpdateComponent } from 'app/entities/tab-param-vitaux/tab-param-vitaux-update.component';
import { TabParamVitauxService } from 'app/entities/tab-param-vitaux/tab-param-vitaux.service';
import { TabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';

describe('Component Tests', () => {
  describe('TabParamVitaux Management Update Component', () => {
    let comp: TabParamVitauxUpdateComponent;
    let fixture: ComponentFixture<TabParamVitauxUpdateComponent>;
    let service: TabParamVitauxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabParamVitauxUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabParamVitauxUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabParamVitauxUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabParamVitauxService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabParamVitaux(123);
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
        const entity = new TabParamVitaux();
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
