import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabConsobstUpdateComponent } from 'app/entities/tab-consobst/tab-consobst-update.component';
import { TabConsobstService } from 'app/entities/tab-consobst/tab-consobst.service';
import { TabConsobst } from 'app/shared/model/tab-consobst.model';

describe('Component Tests', () => {
  describe('TabConsobst Management Update Component', () => {
    let comp: TabConsobstUpdateComponent;
    let fixture: ComponentFixture<TabConsobstUpdateComponent>;
    let service: TabConsobstService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabConsobstUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabConsobstUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabConsobstUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabConsobstService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabConsobst(123);
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
        const entity = new TabConsobst();
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
