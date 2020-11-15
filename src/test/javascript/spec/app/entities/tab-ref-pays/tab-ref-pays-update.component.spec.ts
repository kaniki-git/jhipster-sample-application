import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRefPaysUpdateComponent } from 'app/entities/tab-ref-pays/tab-ref-pays-update.component';
import { TabRefPaysService } from 'app/entities/tab-ref-pays/tab-ref-pays.service';
import { TabRefPays } from 'app/shared/model/tab-ref-pays.model';

describe('Component Tests', () => {
  describe('TabRefPays Management Update Component', () => {
    let comp: TabRefPaysUpdateComponent;
    let fixture: ComponentFixture<TabRefPaysUpdateComponent>;
    let service: TabRefPaysService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRefPaysUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabRefPaysUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabRefPaysUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabRefPaysService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabRefPays(123);
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
        const entity = new TabRefPays();
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
