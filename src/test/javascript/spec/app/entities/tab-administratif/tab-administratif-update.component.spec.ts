import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabAdministratifUpdateComponent } from 'app/entities/tab-administratif/tab-administratif-update.component';
import { TabAdministratifService } from 'app/entities/tab-administratif/tab-administratif.service';
import { TabAdministratif } from 'app/shared/model/tab-administratif.model';

describe('Component Tests', () => {
  describe('TabAdministratif Management Update Component', () => {
    let comp: TabAdministratifUpdateComponent;
    let fixture: ComponentFixture<TabAdministratifUpdateComponent>;
    let service: TabAdministratifService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabAdministratifUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabAdministratifUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabAdministratifUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabAdministratifService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabAdministratif(123);
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
        const entity = new TabAdministratif();
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
