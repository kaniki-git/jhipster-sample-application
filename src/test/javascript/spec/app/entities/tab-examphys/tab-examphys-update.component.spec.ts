import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabExamphysUpdateComponent } from 'app/entities/tab-examphys/tab-examphys-update.component';
import { TabExamphysService } from 'app/entities/tab-examphys/tab-examphys.service';
import { TabExamphys } from 'app/shared/model/tab-examphys.model';

describe('Component Tests', () => {
  describe('TabExamphys Management Update Component', () => {
    let comp: TabExamphysUpdateComponent;
    let fixture: ComponentFixture<TabExamphysUpdateComponent>;
    let service: TabExamphysService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabExamphysUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabExamphysUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabExamphysUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabExamphysService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabExamphys(123);
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
        const entity = new TabExamphys();
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
