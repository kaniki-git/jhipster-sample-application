import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabAccouchement, TabAccouchement } from 'app/shared/model/tab-accouchement.model';
import { TabAccouchementService } from './tab-accouchement.service';

@Component({
  selector: 'jhi-tab-accouchement-update',
  templateUrl: './tab-accouchement-update.component.html',
})
export class TabAccouchementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateAccouche: [],
    matriculeBebe: [],
    sexeBebe: [],
    nomBebe: [],
    prenon1Bebe: [],
    prenon2Bebe: [],
    nomMere: [],
    ageBebe: [],
    ta1Bebe: [],
    ta2Bebe: [],
    poidsBebe: [],
    tailleBebe: [],
    allaitement: [],
    conclusion: [],
  });

  constructor(
    protected tabAccouchementService: TabAccouchementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabAccouchement }) => {
      if (!tabAccouchement.id) {
        const today = moment().startOf('day');
        tabAccouchement.dateAccouche = today;
      }

      this.updateForm(tabAccouchement);
    });
  }

  updateForm(tabAccouchement: ITabAccouchement): void {
    this.editForm.patchValue({
      id: tabAccouchement.id,
      dateAccouche: tabAccouchement.dateAccouche ? tabAccouchement.dateAccouche.format(DATE_TIME_FORMAT) : null,
      matriculeBebe: tabAccouchement.matriculeBebe,
      sexeBebe: tabAccouchement.sexeBebe,
      nomBebe: tabAccouchement.nomBebe,
      prenon1Bebe: tabAccouchement.prenon1Bebe,
      prenon2Bebe: tabAccouchement.prenon2Bebe,
      nomMere: tabAccouchement.nomMere,
      ageBebe: tabAccouchement.ageBebe,
      ta1Bebe: tabAccouchement.ta1Bebe,
      ta2Bebe: tabAccouchement.ta2Bebe,
      poidsBebe: tabAccouchement.poidsBebe,
      tailleBebe: tabAccouchement.tailleBebe,
      allaitement: tabAccouchement.allaitement,
      conclusion: tabAccouchement.conclusion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabAccouchement = this.createFromForm();
    if (tabAccouchement.id !== undefined) {
      this.subscribeToSaveResponse(this.tabAccouchementService.update(tabAccouchement));
    } else {
      this.subscribeToSaveResponse(this.tabAccouchementService.create(tabAccouchement));
    }
  }

  private createFromForm(): ITabAccouchement {
    return {
      ...new TabAccouchement(),
      id: this.editForm.get(['id'])!.value,
      dateAccouche: this.editForm.get(['dateAccouche'])!.value
        ? moment(this.editForm.get(['dateAccouche'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculeBebe: this.editForm.get(['matriculeBebe'])!.value,
      sexeBebe: this.editForm.get(['sexeBebe'])!.value,
      nomBebe: this.editForm.get(['nomBebe'])!.value,
      prenon1Bebe: this.editForm.get(['prenon1Bebe'])!.value,
      prenon2Bebe: this.editForm.get(['prenon2Bebe'])!.value,
      nomMere: this.editForm.get(['nomMere'])!.value,
      ageBebe: this.editForm.get(['ageBebe'])!.value,
      ta1Bebe: this.editForm.get(['ta1Bebe'])!.value,
      ta2Bebe: this.editForm.get(['ta2Bebe'])!.value,
      poidsBebe: this.editForm.get(['poidsBebe'])!.value,
      tailleBebe: this.editForm.get(['tailleBebe'])!.value,
      allaitement: this.editForm.get(['allaitement'])!.value,
      conclusion: this.editForm.get(['conclusion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabAccouchement>>): void {
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
