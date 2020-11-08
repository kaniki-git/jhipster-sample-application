import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabConsObst, TabConsObst } from 'app/shared/model/tab-cons-obst.model';
import { TabConsObstService } from './tab-cons-obst.service';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';

@Component({
  selector: 'jhi-tab-cons-obst-update',
  templateUrl: './tab-cons-obst-update.component.html',
})
export class TabConsObstUpdateComponent implements OnInit {
  isSaving = false;
  tabpatients: ITabPatient[] = [];

  editForm = this.fb.group({
    id: [],
    dateConsult: [],
    ddr: [],
    termeUs: [],
    dateOvulation: [],
    ageGestationel: [],
    termCorrige: [],
    perine: [],
    bassin: [],
    ogtt: [],
    suiviPar: [],
    imc: [],
    poidsMereInitial: [],
    poidsMereTheoriBebe: [],
    tailleMere: [],
    tailleTheoriBebe: [],
    laboTri21: [],
    resumeGrossesse: [],
    conduiteAccouche: [],
    rapport: [],
    tabPatient: [],
  });

  constructor(
    protected tabConsObstService: TabConsObstService,
    protected tabPatientService: TabPatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsObst }) => {
      if (!tabConsObst.id) {
        const today = moment().startOf('day');
        tabConsObst.dateConsult = today;
        tabConsObst.dateOvulation = today;
      }

      this.updateForm(tabConsObst);

      this.tabPatientService
        .query({ filter: 'tabconsobst-is-null' })
        .pipe(
          map((res: HttpResponse<ITabPatient[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabPatient[]) => {
          if (!tabConsObst.tabPatient || !tabConsObst.tabPatient.id) {
            this.tabpatients = resBody;
          } else {
            this.tabPatientService
              .find(tabConsObst.tabPatient.id)
              .pipe(
                map((subRes: HttpResponse<ITabPatient>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabPatient[]) => (this.tabpatients = concatRes));
          }
        });
    });
  }

  updateForm(tabConsObst: ITabConsObst): void {
    this.editForm.patchValue({
      id: tabConsObst.id,
      dateConsult: tabConsObst.dateConsult ? tabConsObst.dateConsult.format(DATE_TIME_FORMAT) : null,
      ddr: tabConsObst.ddr,
      termeUs: tabConsObst.termeUs,
      dateOvulation: tabConsObst.dateOvulation ? tabConsObst.dateOvulation.format(DATE_TIME_FORMAT) : null,
      ageGestationel: tabConsObst.ageGestationel,
      termCorrige: tabConsObst.termCorrige,
      perine: tabConsObst.perine,
      bassin: tabConsObst.bassin,
      ogtt: tabConsObst.ogtt,
      suiviPar: tabConsObst.suiviPar,
      imc: tabConsObst.imc,
      poidsMereInitial: tabConsObst.poidsMereInitial,
      poidsMereTheoriBebe: tabConsObst.poidsMereTheoriBebe,
      tailleMere: tabConsObst.tailleMere,
      tailleTheoriBebe: tabConsObst.tailleTheoriBebe,
      laboTri21: tabConsObst.laboTri21,
      resumeGrossesse: tabConsObst.resumeGrossesse,
      conduiteAccouche: tabConsObst.conduiteAccouche,
      rapport: tabConsObst.rapport,
      tabPatient: tabConsObst.tabPatient,
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
      dateConsult: this.editForm.get(['dateConsult'])!.value
        ? moment(this.editForm.get(['dateConsult'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ddr: this.editForm.get(['ddr'])!.value,
      termeUs: this.editForm.get(['termeUs'])!.value,
      dateOvulation: this.editForm.get(['dateOvulation'])!.value
        ? moment(this.editForm.get(['dateOvulation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ageGestationel: this.editForm.get(['ageGestationel'])!.value,
      termCorrige: this.editForm.get(['termCorrige'])!.value,
      perine: this.editForm.get(['perine'])!.value,
      bassin: this.editForm.get(['bassin'])!.value,
      ogtt: this.editForm.get(['ogtt'])!.value,
      suiviPar: this.editForm.get(['suiviPar'])!.value,
      imc: this.editForm.get(['imc'])!.value,
      poidsMereInitial: this.editForm.get(['poidsMereInitial'])!.value,
      poidsMereTheoriBebe: this.editForm.get(['poidsMereTheoriBebe'])!.value,
      tailleMere: this.editForm.get(['tailleMere'])!.value,
      tailleTheoriBebe: this.editForm.get(['tailleTheoriBebe'])!.value,
      laboTri21: this.editForm.get(['laboTri21'])!.value,
      resumeGrossesse: this.editForm.get(['resumeGrossesse'])!.value,
      conduiteAccouche: this.editForm.get(['conduiteAccouche'])!.value,
      rapport: this.editForm.get(['rapport'])!.value,
      tabPatient: this.editForm.get(['tabPatient'])!.value,
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

  trackById(index: number, item: ITabPatient): any {
    return item.id;
  }
}
