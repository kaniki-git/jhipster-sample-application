import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabGrossesseUpdateComponent } from 'app/entities/tab-grossesse/tab-grossesse-update.component';
import { TabGrossesseService } from 'app/entities/tab-grossesse/tab-grossesse.service';
import { TabGrossesse } from 'app/shared/model/tab-grossesse.model';

describe('Component Tests', () => {
  describe('TabGrossesse Management Update Component', () => {
    let comp: TabGrossesseUpdateComponent;
    let fixture: ComponentFixture<TabGrossesseUpdateComponent>;
    let service: TabGrossesseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabGrossesseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabGrossesseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabGrossesseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabGrossesseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabGrossesse(123);
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
        const entity = new TabGrossesse();
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
