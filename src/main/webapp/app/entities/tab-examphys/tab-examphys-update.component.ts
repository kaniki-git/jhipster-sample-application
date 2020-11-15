import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabExamphys, TabExamphys } from 'app/shared/model/tab-examphys.model';
import { TabExamphysService } from './tab-examphys.service';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';

@Component({
  selector: 'jhi-tab-examphys-update',
  templateUrl: './tab-examphys-update.component.html',
})
export class TabExamphysUpdateComponent implements OnInit {
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
    protected tabExamphysService: TabExamphysService,
    protected tabConsultationService: TabConsultationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabExamphys }) => {
      if (!tabExamphys.id) {
        const today = moment().startOf('day');
        tabExamphys.dateExamPhys = today;
      }

      this.updateForm(tabExamphys);

      this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabconsultations = res.body || []));
    });
  }

  updateForm(tabExamphys: ITabExamphys): void {
    this.editForm.patchValue({
      id: tabExamphys.id,
      dateExamPhys: tabExamphys.dateExamPhys ? tabExamphys.dateExamPhys.format(DATE_TIME_FORMAT) : null,
      nomAppareil: tabExamphys.nomAppareil,
      tete: tabExamphys.tete,
      cou: tabExamphys.cou,
      bouche: tabExamphys.bouche,
      thorax: tabExamphys.thorax,
      ausculationCard: tabExamphys.ausculationCard,
      ausculationPulmo: tabExamphys.ausculationPulmo,
      souffle: tabExamphys.souffle,
      rate: tabExamphys.rate,
      bonchospame: tabExamphys.bonchospame,
      percussionThorax: tabExamphys.percussionThorax,
      abdomen: tabExamphys.abdomen,
      poulsFemoralG: tabExamphys.poulsFemoralG,
      poulsFemoralD: tabExamphys.poulsFemoralD,
      poulsPopliteG: tabExamphys.poulsPopliteG,
      poulsPopliteD: tabExamphys.poulsPopliteD,
      poulsPedieuxG: tabExamphys.poulsPedieuxG,
      poulsPedieuxD: tabExamphys.poulsPedieuxD,
      poulstibialPostG: tabExamphys.poulstibialPostG,
      poulstibialPostD: tabExamphys.poulstibialPostD,
      souffleAbdo: tabExamphys.souffleAbdo,
      souffleFemoralG: tabExamphys.souffleFemoralG,
      souffleFemoralD: tabExamphys.souffleFemoralD,
      spectPeau: tabExamphys.spectPeau,
      examNeuro: tabExamphys.examNeuro,
      rapport: tabExamphys.rapport,
      tabConsultation: tabExamphys.tabConsultation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabExamphys = this.createFromForm();
    if (tabExamphys.id !== undefined) {
      this.subscribeToSaveResponse(this.tabExamphysService.update(tabExamphys));
    } else {
      this.subscribeToSaveResponse(this.tabExamphysService.create(tabExamphys));
    }
  }

  private createFromForm(): ITabExamphys {
    return {
      ...new TabExamphys(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabExamphys>>): void {
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
