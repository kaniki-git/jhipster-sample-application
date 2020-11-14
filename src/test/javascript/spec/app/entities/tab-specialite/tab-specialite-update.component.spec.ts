import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabSpecialiteUpdateComponent } from 'app/entities/tab-specialite/tab-specialite-update.component';
import { TabSpecialiteService } from 'app/entities/tab-specialite/tab-specialite.service';
import { TabSpecialite } from 'app/shared/model/tab-specialite.model';

describe('Component Tests', () => {
  describe('TabSpecialite Management Update Component', () => {
    let comp: TabSpecialiteUpdateComponent;
    let fixture: ComponentFixture<TabSpecialiteUpdateComponent>;
    let service: TabSpecialiteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabSpecialiteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabSpecialiteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabSpecialiteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabSpecialiteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabSpecialite(123);
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
        const entity = new TabSpecialite();
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
