import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabConsObst, TabConsObst } from 'app/shared/model/tab-cons-obst.model';
import { TabConsObstService } from './tab-cons-obst.service';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';

@Component({
  selector: 'jhi-tab-cons-obst-update',
  templateUrl: './tab-cons-obst-update.component.html',
})
export class TabConsObstUpdateComponent implements OnInit {
  isSaving = false;
  tabgynecologies: ITabGynecologie[] = [];

  editForm = this.fb.group({
    id: [],
    dateConsObst: [],
    agePatient: [],
    poids: [],
    tas: [],
    tad: [],
    a: [],
    s: [],
    n: [],
    hu: [],
    bc: [],
    sb: [],
    oe: [],
    pres: [],
    conclusion: [],
    traitement: [],
    suiviPar: [],
    tabGynecologie: [],
  });

  constructor(
    protected tabConsObstService: TabConsObstService,
    protected tabGynecologieService: TabGynecologieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsObst }) => {
      if (!tabConsObst.id) {
        const today = moment().startOf('day');
        tabConsObst.dateConsObst = today;
      }

      this.updateForm(tabConsObst);

      this.tabGynecologieService.query().subscribe((res: HttpResponse<ITabGynecologie[]>) => (this.tabgynecologies = res.body || []));
    });
  }

  updateForm(tabConsObst: ITabConsObst): void {
    this.editForm.patchValue({
      id: tabConsObst.id,
      dateConsObst: tabConsObst.dateConsObst ? tabConsObst.dateConsObst.format(DATE_TIME_FORMAT) : null,
      agePatient: tabConsObst.agePatient,
      poids: tabConsObst.poids,
      tas: tabConsObst.tas,
      tad: tabConsObst.tad,
      a: tabConsObst.a,
      s: tabConsObst.s,
      n: tabConsObst.n,
      hu: tabConsObst.hu,
      bc: tabConsObst.bc,
      sb: tabConsObst.sb,
      oe: tabConsObst.oe,
      pres: tabConsObst.pres,
      conclusion: tabConsObst.conclusion,
      traitement: tabConsObst.traitement,
      suiviPar: tabConsObst.suiviPar,
      tabGynecologie: tabConsObst.tabGynecologie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabConsObst = this.createFromForm();
    if (tabConsObst.id !== undefined) {
      this.subscribeToSaveResponse(this.tabConsObstService.update(tabConsObst));
    } else {
      this.subscribeToSaveResponse(this.tabConsObstService.create(tabConsObst));
    }
  }

  private createFromForm(): ITabConsObst {
    return {
      ...new TabConsObst(),
      id: this.editForm.get(['id'])!.value,
      dateConsObst: this.editForm.get(['dateConsObst'])!.value
        ? moment(this.editForm.get(['dateConsObst'])!.value, DATE_TIME_FORMAT)
        : undefined,
      agePatient: this.editForm.get(['agePatient'])!.value,
      poids: this.editForm.get(['poids'])!.value,
      tas: this.editForm.get(['tas'])!.value,
      tad: this.editForm.get(['tad'])!.value,
      a: this.editForm.get(['a'])!.value,
      s: this.editForm.get(['s'])!.value,
      n: this.editForm.get(['n'])!.value,
      hu: this.editForm.get(['hu'])!.value,
      bc: this.editForm.get(['bc'])!.value,
      sb: this.editForm.get(['sb'])!.value,
      oe: this.editForm.get(['oe'])!.value,
      pres: this.editForm.get(['pres'])!.value,
      conclusion: this.editForm.get(['conclusion'])!.value,
      traitement: this.editForm.get(['traitement'])!.value,
      suiviPar: this.editForm.get(['suiviPar'])!.value,
      tabGynecologie: this.editForm.get(['tabGynecologie'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabConsObst>>): void {
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

  trackById(index: number, item: ITabGynecologie): any {
    return item.id;
  }
}
