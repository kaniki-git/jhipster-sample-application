import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabUrgence, TabUrgence } from 'app/shared/model/tab-urgence.model';
import { TabUrgenceService } from './tab-urgence.service';
import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';
import { TabAdministratifService } from 'app/entities/tab-administratif/tab-administratif.service';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';

type SelectableEntity = ITabAdministratif | ITabConsultation;

@Component({
  selector: 'jhi-tab-urgence-update',
  templateUrl: './tab-urgence-update.component.html',
})
export class TabUrgenceUpdateComponent implements OnInit {
  isSaving = false;
  tabadministratifs: ITabAdministratif[] = [];
  tabconsultations: ITabConsultation[] = [];

  editForm = this.fb.group({
    id: [],
    dateArriveeUrgence: [],
    provenancePatient: [],
    ledevenirPatient: [],
    rapportUrgence: [],
    tabAdministratif: [],
    tabConsultation: [],
  });

  constructor(
    protected tabUrgenceService: TabUrgenceService,
    protected tabAdministratifService: TabAdministratifService,
    protected tabConsultationService: TabConsultationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUrgence }) => {
      if (!tabUrgence.id) {
        const today = moment().startOf('day');
        tabUrgence.dateArriveeUrgence = today;
      }

      this.updateForm(tabUrgence);

      this.tabAdministratifService.query().subscribe((res: HttpResponse<ITabAdministratif[]>) => (this.tabadministratifs = res.body || []));

      this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabconsultations = res.body || []));
    });
  }

  updateForm(tabUrgence: ITabUrgence): void {
    this.editForm.patchValue({
      id: tabUrgence.id,
      dateArriveeUrgence: tabUrgence.dateArriveeUrgence ? tabUrgence.dateArriveeUrgence.format(DATE_TIME_FORMAT) : null,
      provenancePatient: tabUrgence.provenancePatient,
      ledevenirPatient: tabUrgence.ledevenirPatient,
      rapportUrgence: tabUrgence.rapportUrgence,
      tabAdministratif: tabUrgence.tabAdministratif,
      tabConsultation: tabUrgence.tabConsultation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabUrgence = this.createFromForm();
    if (tabUrgence.id !== undefined) {
      this.subscribeToSaveResponse(this.tabUrgenceService.update(tabUrgence));
    } else {
      this.subscribeToSaveResponse(this.tabUrgenceService.create(tabUrgence));
    }
  }

  private createFromForm(): ITabUrgence {
    return {
      ...new TabUrgence(),
      id: this.editForm.get(['id'])!.value,
      dateArriveeUrgence: this.editForm.get(['dateArriveeUrgence'])!.value
        ? moment(this.editForm.get(['dateArriveeUrgence'])!.value, DATE_TIME_FORMAT)
        : undefined,
      provenancePatient: this.editForm.get(['provenancePatient'])!.value,
      ledevenirPatient: this.editForm.get(['ledevenirPatient'])!.value,
      rapportUrgence: this.editForm.get(['rapportUrgence'])!.value,
      tabAdministratif: this.editForm.get(['tabAdministratif'])!.value,
      tabConsultation: this.editForm.get(['tabConsultation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabUrgence>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
