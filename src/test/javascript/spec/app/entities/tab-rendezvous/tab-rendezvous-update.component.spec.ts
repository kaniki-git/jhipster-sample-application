import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabRendezvousUpdateComponent } from 'app/entities/tab-rendezvous/tab-rendezvous-update.component';
import { TabRendezvousService } from 'app/entities/tab-rendezvous/tab-rendezvous.service';
import { TabRendezvous } from 'app/shared/model/tab-rendezvous.model';

describe('Component Tests', () => {
  describe('TabRendezvous Management Update Component', () => {
    let comp: TabRendezvousUpdateComponent;
    let fixture: ComponentFixture<TabRendezvousUpdateComponent>;
    let service: TabRendezvousService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabRendezvousUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TabRendezvousUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TabRendezvousUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabRendezvousService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TabRendezvous(123);
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
        const entity = new TabRendezvous();
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
