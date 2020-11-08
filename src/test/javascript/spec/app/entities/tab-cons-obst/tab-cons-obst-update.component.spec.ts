import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsObstUpdateComponent } from 'app/entities/tab-cons-obst/tab-cons-obst-update.component';
import { TabConsObstService } from 'app/entities/tab-cons-obst/tab-cons-obst.service';
import { TabConsObst } from 'app/shared/model/tab-cons-obst.model';

describe('Component Tests', () => {
  describe('TabConsObst Management Update Component', () => {
    let comp: TabConsObstUpdateComponent;
    let fixture: ComponentFixture<TabConsObstUpdateComponent>;
    let service: TabConsObstService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsObstUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabConsObstUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabConsObstUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabConsObstService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabConsObst(123);
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
        const entity = new TabConsObst();
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
