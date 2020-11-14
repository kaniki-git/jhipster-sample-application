import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabGynecologieUpdateComponent } from 'app/entities/tab-gynecologie/tab-gynecologie-update.component';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';
import { TabGynecologie } from 'app/shared/model/tab-gynecologie.model';

describe('Component Tests', () => {
  describe('TabGynecologie Management Update Component', () => {
    let comp: TabGynecologieUpdateComponent;
    let fixture: ComponentFixture<TabGynecologieUpdateComponent>;
    let service: TabGynecologieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabGynecologieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabGynecologieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabGynecologieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabGynecologieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabGynecologie(123);
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
        const entity = new TabGynecologie();
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
