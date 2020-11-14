import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabCoordonneeUpdateComponent } from 'app/entities/tab-coordonnee/tab-coordonnee-update.component';
import { TabCoordonneeService } from 'app/entities/tab-coordonnee/tab-coordonnee.service';
import { TabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

describe('Component Tests', () => {
  describe('TabCoordonnee Management Update Component', () => {
    let comp: TabCoordonneeUpdateComponent;
    let fixture: ComponentFixture<TabCoordonneeUpdateComponent>;
    let service: TabCoordonneeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabCoordonneeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabCoordonneeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabCoordonneeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabCoordonneeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabCoordonnee(123);
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
        const entity = new TabCoordonnee();
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
