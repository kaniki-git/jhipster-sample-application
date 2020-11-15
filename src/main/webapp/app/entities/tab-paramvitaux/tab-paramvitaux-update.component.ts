import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabParamvitaux, TabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';
import { TabParamvitauxService } from './tab-paramvitaux.service';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';

type SelectableEntity = ITabConsultation | ITabGynecologie;

@Component({
  selector: 'jhi-tab-paramvitaux-update',
  templateUrl: './tab-paramvitaux-update.component.html',
})
export class TabParamvitauxUpdateComponent implements OnInit {
  isSaving = false;
  tabconsultations: ITabConsultation[] = [];
  tabgynecologies: ITabGynecologie[] = [];

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
    tabConsultation: [],
    tabGynecologie: [],
  });

  constructor(
    protected tabParamvitauxService: TabParamvitauxService,
    protected tabConsultationService: TabConsultationService,
    protected tabGynecologieService: TabGynecologieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabParamvitaux }) => {
      if (!tabParamvitaux.id) {
        const today = moment().startOf('day');
        tabParamvitaux.dateExam = today;
      }

      this.updateForm(tabParamvitaux);

      this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabconsultations = res.body || []));

      this.tabGynecologieService.query().subscribe((res: HttpResponse<ITabGynecologie[]>) => (this.tabgynecologies = res.body || []));
    });
  }

  updateForm(tabParamvitaux: ITabParamvitaux): void {
    this.editForm.patchValue({
      id: tabParamvitaux.id,
      dateExam: tabParamvitaux.dateExam ? tabParamvitaux.dateExam.format(DATE_TIME_FORMAT) : null,
      ta1: tabParamvitaux.ta1,
      ta2: tabParamvitaux.ta2,
      pouls: tabParamvitaux.pouls,
      temperature: tabParamvitaux.temperature,
      frequence: tabParamvitaux.frequence,
      taille: tabParamvitaux.taille,
      sa02: tabParamvitaux.sa02,
      sous: tabParamvitaux.sous,
      poids: tabParamvitaux.poids,
      tabConsultation: tabParamvitaux.tabConsultation,
      tabGynecologie: tabParamvitaux.tabGynecologie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabParamvitaux = this.createFromForm();
    if (tabParamvitaux.id !== undefined) {
      this.subscribeToSaveResponse(this.tabParamvitauxService.update(tabParamvitaux));
    } else {
      this.subscribeToSaveResponse(this.tabParamvitauxService.create(tabParamvitaux));
    }
  }

  private createFromForm(): ITabParamvitaux {
    return {
      ...new TabParamvitaux(),
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
      tabConsultation: this.editForm.get(['tabConsultation'])!.value,
      tabGynecologie: this.editForm.get(['tabGynecologie'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabParamvitaux>>): void {
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
