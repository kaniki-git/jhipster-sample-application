import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabConsobst, TabConsobst } from 'app/shared/model/tab-consobst.model';
import { TabConsobstService } from './tab-consobst.service';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';

@Component({
  selector: 'jhi-tab-consobst-update',
  templateUrl: './tab-consobst-update.component.html',
})
export class TabConsobstUpdateComponent implements OnInit {
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
    protected tabConsobstService: TabConsobstService,
    protected tabGynecologieService: TabGynecologieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsobst }) => {
      if (!tabConsobst.id) {
        const today = moment().startOf('day');
        tabConsobst.dateConsObst = today;
      }

      this.updateForm(tabConsobst);

      this.tabGynecologieService.query().subscribe((res: HttpResponse<ITabGynecologie[]>) => (this.tabgynecologies = res.body || []));
    });
  }

  updateForm(tabConsobst: ITabConsobst): void {
    this.editForm.patchValue({
      id: tabConsobst.id,
      dateConsObst: tabConsobst.dateConsObst ? tabConsobst.dateConsObst.format(DATE_TIME_FORMAT) : null,
      agePatient: tabConsobst.agePatient,
      poids: tabConsobst.poids,
      tas: tabConsobst.tas,
      tad: tabConsobst.tad,
      a: tabConsobst.a,
      s: tabConsobst.s,
      n: tabConsobst.n,
      hu: tabConsobst.hu,
      bc: tabConsobst.bc,
      sb: tabConsobst.sb,
      oe: tabConsobst.oe,
      pres: tabConsobst.pres,
      conclusion: tabConsobst.conclusion,
      traitement: tabConsobst.traitement,
      suiviPar: tabConsobst.suiviPar,
      tabGynecologie: tabConsobst.tabGynecologie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabConsobst = this.createFromForm();
    if (tabConsobst.id !== undefined) {
      this.subscribeToSaveResponse(this.tabConsobstService.update(tabConsobst));
    } else {
      this.subscribeToSaveResponse(this.tabConsobstService.create(tabConsobst));
    }
  }

  private createFromForm(): ITabConsobst {
    return {
      ...new TabConsobst(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabConsobst>>): void {
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
