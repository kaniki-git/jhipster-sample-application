import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabParamVitaux, TabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { TabParamVitauxService } from './tab-param-vitaux.service';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';
import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { TabUrgenceService } from 'app/entities/tab-urgence/tab-urgence.service';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from 'app/entities/tab-hospital/tab-hospital.service';

type SelectableEntity = ITabConsultation | ITabUrgence | ITabHospital;

@Component({
  selector: 'jhi-tab-param-vitaux-update',
  templateUrl: './tab-param-vitaux-update.component.html',
})
export class TabParamVitauxUpdateComponent implements OnInit {
  isSaving = false;
  tabconsultations: ITabConsultation[] = [];
  taburgences: ITabUrgence[] = [];
  tabhospitals: ITabHospital[] = [];

  editForm = this.fb.group({
    id: [],
    dateExam: [],
    ta1: [],
    ta2: [],
    pouls: [],
    temperature: [],
    frequence: [],
    taille: [],
    sa02: [],
    sous: [],
    poids: [],
    examenPhysique: [],
    examenCompl: [],
    evolution: [],
    tabConsultation: [],
    tabUrgence: [],
    tabHospital: [],
  });

  constructor(
    protected tabParamVitauxService: TabParamVitauxService,
    protected tabConsultationService: TabConsultationService,
    protected tabUrgenceService: TabUrgenceService,
    protected tabHospitalService: TabHospitalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabParamVitaux }) => {
      if (!tabParamVitaux.id) {
        const today = moment().startOf('day');
        tabParamVitaux.dateExam = today;
      }

      this.updateForm(tabParamVitaux);

      this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabconsultations = res.body || []));

      this.tabUrgenceService.query().subscribe((res: HttpResponse<ITabUrgence[]>) => (this.taburgences = res.body || []));

      this.tabHospitalService.query().subscribe((res: HttpResponse<ITabHospital[]>) => (this.tabhospitals = res.body || []));
    });
  }

  updateForm(tabParamVitaux: ITabParamVitaux): void {
    this.editForm.patchValue({
      id: tabParamVitaux.id,
      dateExam: tabParamVitaux.dateExam ? tabParamVitaux.dateExam.format(DATE_TIME_FORMAT) : null,
      ta1: tabParamVitaux.ta1,
      ta2: tabParamVitaux.ta2,
      pouls: tabParamVitaux.pouls,
      temperature: tabParamVitaux.temperature,
      frequence: tabParamVitaux.frequence,
      taille: tabParamVitaux.taille,
      sa02: tabParamVitaux.sa02,
      sous: tabParamVitaux.sous,
      poids: tabParamVitaux.poids,
      examenPhysique: tabParamVitaux.examenPhysique,
      examenCompl: tabParamVitaux.examenCompl,
      evolution: tabParamVitaux.evolution,
      tabConsultation: tabParamVitaux.tabConsultation,
      tabUrgence: tabParamVitaux.tabUrgence,
      tabHospital: tabParamVitaux.tabHospital,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabParamVitaux = this.createFromForm();
    if (tabParamVitaux.id !== undefined) {
      this.subscribeToSaveResponse(this.tabParamVitauxService.update(tabParamVitaux));
    } else {
      this.subscribeToSaveResponse(this.tabParamVitauxService.create(tabParamVitaux));
    }
  }

  private createFromForm(): ITabParamVitaux {
    return {
      ...new TabParamVitaux(),
      id: this.editForm.get(['id'])!.value,
      dateExam: this.editForm.get(['dateExam'])!.value ? moment(this.editForm.get(['dateExam'])!.value, DATE_TIME_FORMAT) : undefined,
      ta1: this.editForm.get(['ta1'])!.value,
      ta2: this.editForm.get(['ta2'])!.value,
      pouls: this.editForm.get(['pouls'])!.value,
      temperature: this.editForm.get(['temperature'])!.value,
      frequence: this.editForm.get(['frequence'])!.value,
      taille: this.editForm.get(['taille'])!.value,
      sa02: this.editForm.get(['sa02'])!.value,
      sous: this.editForm.get(['sous'])!.value,
      poids: this.editForm.get(['poids'])!.value,
      examenPhysique: this.editForm.get(['examenPhysique'])!.value,
      examenCompl: this.editForm.get(['examenCompl'])!.value,
      evolution: this.editForm.get(['evolution'])!.value,
      tabConsultation: this.editForm.get(['tabConsultation'])!.value,
      tabUrgence: this.editForm.get(['tabUrgence'])!.value,
      tabHospital: this.editForm.get(['tabHospital'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabParamVitaux>>): void {
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
