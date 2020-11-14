import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUserUpdateComponent } from 'app/entities/tab-user/tab-user-update.component';
import { TabUserService } from 'app/entities/tab-user/tab-user.service';
import { TabUser } from 'app/shared/model/tab-user.model';

describe('Component Tests', () => {
  describe('TabUser Management Update Component', () => {
    let comp: TabUserUpdateComponent;
    let fixture: ComponentFixture<TabUserUpdateComponent>;
    let service: TabUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUserUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabUser(123);
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
        const entity = new TabUser();
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
