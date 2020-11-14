import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabExamPhys, TabExamPhys } from 'app/shared/model/tab-exam-phys.model';
import { TabExamPhysService } from './tab-exam-phys.service';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';

@Component({
  selector: 'jhi-tab-exam-phys-update',
  templateUrl: './tab-exam-phys-update.component.html',
})
export class TabExamPhysUpdateComponent implements OnInit {
  isSaving = false;
  tabconsultations: ITabConsultation[] = [];

  editForm = this.fb.group({
    id: [],
    dateExamPhys: [],
    nomAppareil: [],
    tete: [],
    cou: [],
    bouche: [],
    thorax: [],
    ausculationCard: [],
    ausculationPulmo: [],
    souffle: [],
    rate: [],
    bonchospame: [],
    percussionThorax: [],
    abdomen: [],
    poulsFemoralG: [],
    poulsFemoralD: [],
    poulsPopliteG: [],
    poulsPopliteD: [],
    poulsPedieuxG: [],
    poulsPedieuxD: [],
    poulstibialPostG: [],
    poulstibialPostD: [],
    souffleAbdo: [],
    souffleFemoralG: [],
    souffleFemoralD: [],
    spectPeau: [],
    examNeuro: [],
    rapport: [],
    tabConsultation: [],
  });

  constructor(
    protected tabExamPhysService: TabExamPhysService,
    protected tabConsultationService: TabConsultationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabExamPhys }) => {
      if (!tabExamPhys.id) {
        const today = moment().startOf('day');
        tabExamPhys.dateExamPhys = today;
      }

      this.updateForm(tabExamPhys);

      this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabconsultations = res.body || []));
    });
  }

  updateForm(tabExamPhys: ITabExamPhys): void {
    this.editForm.patchValue({
      id: tabExamPhys.id,
      dateExamPhys: tabExamPhys.dateExamPhys ? tabExamPhys.dateExamPhys.format(DATE_TIME_FORMAT) : null,
      nomAppareil: tabExamPhys.nomAppareil,
      tete: tabExamPhys.tete,
      cou: tabExamPhys.cou,
      bouche: tabExamPhys.bouche,
      thorax: tabExamPhys.thorax,
      ausculationCard: tabExamPhys.ausculationCard,
      ausculationPulmo: tabExamPhys.ausculationPulmo,
      souffle: tabExamPhys.souffle,
      rate: tabExamPhys.rate,
      bonchospame: tabExamPhys.bonchospame,
      percussionThorax: tabExamPhys.percussionThorax,
      abdomen: tabExamPhys.abdomen,
      poulsFemoralG: tabExamPhys.poulsFemoralG,
      poulsFemoralD: tabExamPhys.poulsFemoralD,
      poulsPopliteG: tabExamPhys.poulsPopliteG,
      poulsPopliteD: tabExamPhys.poulsPopliteD,
      poulsPedieuxG: tabExamPhys.poulsPedieuxG,
      poulsPedieuxD: tabExamPhys.poulsPedieuxD,
      poulstibialPostG: tabExamPhys.poulstibialPostG,
      poulstibialPostD: tabExamPhys.poulstibialPostD,
      souffleAbdo: tabExamPhys.souffleAbdo,
      souffleFemoralG: tabExamPhys.souffleFemoralG,
      souffleFemoralD: tabExamPhys.souffleFemoralD,
      spectPeau: tabExamPhys.spectPeau,
      examNeuro: tabExamPhys.examNeuro,
      rapport: tabExamPhys.rapport,
      tabConsultation: tabExamPhys.tabConsultation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabExamPhys = this.createFromForm();
    if (tabExamPhys.id !== undefined) {
      this.subscribeToSaveResponse(this.tabExamPhysService.update(tabExamPhys));
    } else {
      this.subscribeToSaveResponse(this.tabExamPhysService.create(tabExamPhys));
    }
  }

  private createFromForm(): ITabExamPhys {
    return {
      ...new TabExamPhys(),
      id: this.editForm.get(['id'])!.value,
      dateExamPhys: this.editForm.get(['dateExamPhys'])!.value
        ? moment(this.editForm.get(['dateExamPhys'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nomAppareil: this.editForm.get(['nomAppareil'])!.value,
      tete: this.editForm.get(['tete'])!.value,
      cou: this.editForm.get(['cou'])!.value,
      bouche: this.editForm.get(['bouche'])!.value,
      thorax: this.editForm.get(['thorax'])!.value,
      ausculationCard: this.editForm.get(['ausculationCard'])!.value,
      ausculationPulmo: this.editForm.get(['ausculationPulmo'])!.value,
      souffle: this.editForm.get(['souffle'])!.value,
      rate: this.editForm.get(['rate'])!.value,
      bonchospame: this.editForm.get(['bonchospame'])!.value,
      percussionThorax: this.editForm.get(['percussionThorax'])!.value,
      abdomen: this.editForm.get(['abdomen'])!.value,
      poulsFemoralG: this.editForm.get(['poulsFemoralG'])!.value,
      poulsFemoralD: this.editForm.get(['poulsFemoralD'])!.value,
      poulsPopliteG: this.editForm.get(['poulsPopliteG'])!.value,
      poulsPopliteD: this.editForm.get(['poulsPopliteD'])!.value,
      poulsPedieuxG: this.editForm.get(['poulsPedieuxG'])!.value,
      poulsPedieuxD: this.editForm.get(['poulsPedieuxD'])!.value,
      poulstibialPostG: this.editForm.get(['poulstibialPostG'])!.value,
      poulstibialPostD: this.editForm.get(['poulstibialPostD'])!.value,
      souffleAbdo: this.editForm.get(['souffleAbdo'])!.value,
      souffleFemoralG: this.editForm.get(['souffleFemoralG'])!.value,
      souffleFemoralD: this.editForm.get(['souffleFemoralD'])!.value,
      spectPeau: this.editForm.get(['spectPeau'])!.value,
      examNeuro: this.editForm.get(['examNeuro'])!.value,
      rapport: this.editForm.get(['rapport'])!.value,
      tabConsultation: this.editForm.get(['tabConsultation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabExamPhys>>): void {
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

  trackById(index: number, item: ITabConsultation): any {
    return item.id;
  }
}
