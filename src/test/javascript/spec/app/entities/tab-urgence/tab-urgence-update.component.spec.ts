import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUrgenceUpdateComponent } from 'app/entities/tab-urgence/tab-urgence-update.component';
import { TabUrgenceService } from 'app/entities/tab-urgence/tab-urgence.service';
import { TabUrgence } from 'app/shared/model/tab-urgence.model';

describe('Component Tests', () => {
  describe('TabUrgence Management Update Component', () => {
    let comp: TabUrgenceUpdateComponent;
    let fixture: ComponentFixture<TabUrgenceUpdateComponent>;
    let service: TabUrgenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUrgenceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabUrgenceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabUrgenceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabUrgenceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabUrgence(123);
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
        const entity = new TabUrgence();
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
