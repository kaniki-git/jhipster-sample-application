import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabPersonnelUpdateComponent } from 'app/entities/tab-personnel/tab-personnel-update.component';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';
import { TabPersonnel } from 'app/shared/model/tab-personnel.model';

describe('Component Tests', () => {
  describe('TabPersonnel Management Update Component', () => {
    let comp: TabPersonnelUpdateComponent;
    let fixture: ComponentFixture<TabPersonnelUpdateComponent>;
    let service: TabPersonnelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabPersonnelUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabPersonnelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabPersonnelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabPersonnelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabPersonnel(123);
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
        const entity = new TabPersonnel();
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
