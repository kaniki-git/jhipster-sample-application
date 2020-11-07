import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabGrossesse, TabGrossesse } from 'app/shared/model/tab-grossesse.model';
import { TabGrossesseService } from './tab-grossesse.service';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';

@Component({
  selector: 'jhi-tab-grossesse-update',
  templateUrl: './tab-grossesse-update.component.html',
})
export class TabGrossesseUpdateComponent implements OnInit {
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
    protected tabGrossesseService: TabGrossesseService,
    protected tabPatientService: TabPatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabGrossesse }) => {
      if (!tabGrossesse.id) {
        const today = moment().startOf('day');
        tabGrossesse.dateConsult = today;
        tabGrossesse.dateOvulation = today;
      }

      this.updateForm(tabGrossesse);

      this.tabPatientService
        .query({ filter: 'tabgrossesse-is-null' })
        .pipe(
          map((res: HttpResponse<ITabPatient[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabPatient[]) => {
          if (!tabGrossesse.tabPatient || !tabGrossesse.tabPatient.id) {
            this.tabpatients = resBody;
          } else {
            this.tabPatientService
              .find(tabGrossesse.tabPatient.id)
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

  updateForm(tabGrossesse: ITabGrossesse): void {
    this.editForm.patchValue({
      id: tabGrossesse.id,
      dateConsult: tabGrossesse.dateConsult ? tabGrossesse.dateConsult.format(DATE_TIME_FORMAT) : null,
      ddr: tabGrossesse.ddr,
      termeUs: tabGrossesse.termeUs,
      dateOvulation: tabGrossesse.dateOvulation ? tabGrossesse.dateOvulation.format(DATE_TIME_FORMAT) : null,
      ageGestationel: tabGrossesse.ageGestationel,
      termCorrige: tabGrossesse.termCorrige,
      perine: tabGrossesse.perine,
      bassin: tabGrossesse.bassin,
      ogtt: tabGrossesse.ogtt,
      suiviPar: tabGrossesse.suiviPar,
      imc: tabGrossesse.imc,
      poidsMereInitial: tabGrossesse.poidsMereInitial,
      poidsMereTheoriBebe: tabGrossesse.poidsMereTheoriBebe,
      tailleMere: tabGrossesse.tailleMere,
      tailleTheoriBebe: tabGrossesse.tailleTheoriBebe,
      laboTri21: tabGrossesse.laboTri21,
      resumeGrossesse: tabGrossesse.resumeGrossesse,
      conduiteAccouche: tabGrossesse.conduiteAccouche,
      rapport: tabGrossesse.rapport,
      tabPatient: tabGrossesse.tabPatient,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabGrossesse = this.createFromForm();
    if (tabGrossesse.id !== undefined) {
      this.subscribeToSaveResponse(this.tabGrossesseService.update(tabGrossesse));
    } else {
      this.subscribeToSaveResponse(this.tabGrossesseService.create(tabGrossesse));
    }
  }

  private createFromForm(): ITabGrossesse {
    return {
      ...new TabGrossesse(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabGrossesse>>): void {
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
