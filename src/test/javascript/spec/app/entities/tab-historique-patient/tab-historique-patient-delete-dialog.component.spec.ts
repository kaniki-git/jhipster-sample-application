import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TabHistoriquePatientDeleteDialogComponent } from 'app/entities/tab-historique-patient/tab-historique-patient-delete-dialog.component';
import { TabHistoriquePatientService } from 'app/entities/tab-historique-patient/tab-historique-patient.service';

describe('Component Tests', () => {
  describe('TabHistoriquePatient Management Delete Component', () => {
    let comp: TabHistoriquePatientDeleteDialogComponent;
    let fixture: ComponentFixture<TabHistoriquePatientDeleteDialogComponent>;
    let service: TabHistoriquePatientService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabHistoriquePatientDeleteDialogComponent],
      })
        .overrideTemplate(TabHistoriquePatientDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabHistoriquePatientDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TabHistoriquePatientService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
