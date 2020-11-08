import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabSerologieUpdateComponent } from 'app/entities/tab-serologie/tab-serologie-update.component';
import { TabSerologieService } from 'app/entities/tab-serologie/tab-serologie.service';
import { TabSerologie } from 'app/shared/model/tab-serologie.model';

describe('Component Tests', () => {
  describe('TabSerologie Management Update Component', () => {
    let comp: TabSerologieUpdateComponent;
    let fixture: ComponentFixture<TabSerologieUpdateComponent>;
    let service: TabSerologieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabSerologieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabSerologieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabSerologieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabSerologieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabSerologie(123);
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
        const entity = new TabSerologie();
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
