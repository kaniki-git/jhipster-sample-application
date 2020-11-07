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

@Component({
  selector: 'jhi-tab-urgence-update',
  templateUrl: './tab-urgence-update.component.html',
})
export class TabUrgenceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateArriveeUrgence: [],
    dateDepartUrgence: [],
    rapportUrgence: [],
  });

  constructor(protected tabUrgenceService: TabUrgenceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUrgence }) => {
      if (!tabUrgence.id) {
        const today = moment().startOf('day');
        tabUrgence.dateArriveeUrgence = today;
        tabUrgence.dateDepartUrgence = today;
      }

      this.updateForm(tabUrgence);
    });
  }

  updateForm(tabUrgence: ITabUrgence): void {
    this.editForm.patchValue({
      id: tabUrgence.id,
      dateArriveeUrgence: tabUrgence.dateArriveeUrgence ? tabUrgence.dateArriveeUrgence.format(DATE_TIME_FORMAT) : null,
      dateDepartUrgence: tabUrgence.dateDepartUrgence ? tabUrgence.dateDepartUrgence.format(DATE_TIME_FORMAT) : null,
      rapportUrgence: tabUrgence.rapportUrgence,
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
      dateDepartUrgence: this.editForm.get(['dateDepartUrgence'])!.value
        ? moment(this.editForm.get(['dateDepartUrgence'])!.value, DATE_TIME_FORMAT)
        : undefined,
      rapportUrgence: this.editForm.get(['rapportUrgence'])!.value,
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
}
