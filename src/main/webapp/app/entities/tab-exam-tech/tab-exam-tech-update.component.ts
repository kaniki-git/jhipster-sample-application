import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabExamTech, TabExamTech } from 'app/shared/model/tab-exam-tech.model';
import { TabExamTechService } from './tab-exam-tech.service';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from 'app/entities/tab-hospital/tab-hospital.service';

@Component({
  selector: 'jhi-tab-exam-tech-update',
  templateUrl: './tab-exam-tech-update.component.html',
})
export class TabExamTechUpdateComponent implements OnInit {
  isSaving = false;
  tabhospitals: ITabHospital[] = [];

  editForm = this.fb.group({
    id: [],
    typeExamTech: [],
    dateExamTech: [],
    tarifExamTech: [],
    conclusionExamTech: [],
    tabHospital: [],
  });

  constructor(
    protected tabExamTechService: TabExamTechService,
    protected tabHospitalService: TabHospitalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabExamTech }) => {
      if (!tabExamTech.id) {
        const today = moment().startOf('day');
        tabExamTech.dateExamTech = today;
        tabExamTech.tarifExamTech = today;
      }

      this.updateForm(tabExamTech);

      this.tabHospitalService.query().subscribe((res: HttpResponse<ITabHospital[]>) => (this.tabhospitals = res.body || []));
    });
  }

  updateForm(tabExamTech: ITabExamTech): void {
    this.editForm.patchValue({
      id: tabExamTech.id,
      typeExamTech: tabExamTech.typeExamTech,
      dateExamTech: tabExamTech.dateExamTech ? tabExamTech.dateExamTech.format(DATE_TIME_FORMAT) : null,
      tarifExamTech: tabExamTech.tarifExamTech ? tabExamTech.tarifExamTech.format(DATE_TIME_FORMAT) : null,
      conclusionExamTech: tabExamTech.conclusionExamTech,
      tabHospital: tabExamTech.tabHospital,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabExamTech = this.createFromForm();
    if (tabExamTech.id !== undefined) {
      this.subscribeToSaveResponse(this.tabExamTechService.update(tabExamTech));
    } else {
      this.subscribeToSaveResponse(this.tabExamTechService.create(tabExamTech));
    }
  }

  private createFromForm(): ITabExamTech {
    return {
      ...new TabExamTech(),
      id: this.editForm.get(['id'])!.value,
      typeExamTech: this.editForm.get(['typeExamTech'])!.value,
      dateExamTech: this.editForm.get(['dateExamTech'])!.value
        ? moment(this.editForm.get(['dateExamTech'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tarifExamTech: this.editForm.get(['tarifExamTech'])!.value
        ? moment(this.editForm.get(['tarifExamTech'])!.value, DATE_TIME_FORMAT)
        : undefined,
      conclusionExamTech: this.editForm.get(['conclusionExamTech'])!.value,
      tabHospital: this.editForm.get(['tabHospital'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabExamTech>>): void {
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

  trackById(index: number, item: ITabHospital): any {
    return item.id;
  }
}
