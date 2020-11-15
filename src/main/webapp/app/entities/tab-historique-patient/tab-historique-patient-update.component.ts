import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabHistoriquePatient, TabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';
import { TabHistoriquePatientService } from './tab-historique-patient.service';

@Component({
  selector: 'jhi-tab-historique-patient-update',
  templateUrl: './tab-historique-patient-update.component.html',
})
export class TabHistoriquePatientUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    numeroDossier: [],
    matriculePersonnel: [],
    matriculecreation: [],
    datecreation: [],
    matriculemodif: [],
    datemodif: [],
  });

  constructor(
    protected tabHistoriquePatientService: TabHistoriquePatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabHistoriquePatient }) => {
      if (!tabHistoriquePatient.id) {
        const today = moment().startOf('day');
        tabHistoriquePatient.datecreation = today;
        tabHistoriquePatient.datemodif = today;
      }

      this.updateForm(tabHistoriquePatient);
    });
  }

  updateForm(tabHistoriquePatient: ITabHistoriquePatient): void {
    this.editForm.patchValue({
      id: tabHistoriquePatient.id,
      numeroDossier: tabHistoriquePatient.numeroDossier,
      matriculePersonnel: tabHistoriquePatient.matriculePersonnel,
      matriculecreation: tabHistoriquePatient.matriculecreation,
      datecreation: tabHistoriquePatient.datecreation ? tabHistoriquePatient.datecreation.format(DATE_TIME_FORMAT) : null,
      matriculemodif: tabHistoriquePatient.matriculemodif,
      datemodif: tabHistoriquePatient.datemodif ? tabHistoriquePatient.datemodif.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabHistoriquePatient = this.createFromForm();
    if (tabHistoriquePatient.id !== undefined) {
      this.subscribeToSaveResponse(this.tabHistoriquePatientService.update(tabHistoriquePatient));
    } else {
      this.subscribeToSaveResponse(this.tabHistoriquePatientService.create(tabHistoriquePatient));
    }
  }

  private createFromForm(): ITabHistoriquePatient {
    return {
      ...new TabHistoriquePatient(),
      id: this.editForm.get(['id'])!.value,
      numeroDossier: this.editForm.get(['numeroDossier'])!.value,
      matriculePersonnel: this.editForm.get(['matriculePersonnel'])!.value,
      matriculecreation: this.editForm.get(['matriculecreation'])!.value,
      datecreation: this.editForm.get(['datecreation'])!.value
        ? moment(this.editForm.get(['datecreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculemodif: this.editForm.get(['matriculemodif'])!.value,
      datemodif: this.editForm.get(['datemodif'])!.value ? moment(this.editForm.get(['datemodif'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabHistoriquePatient>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
