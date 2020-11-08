import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabPatient, TabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from './tab-patient.service';
import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';
import { TabAdministratifService } from 'app/entities/tab-administratif/tab-administratif.service';
import { ITabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';
import { TabHistoriquePatientService } from 'app/entities/tab-historique-patient/tab-historique-patient.service';

type SelectableEntity = ITabAdministratif | ITabHistoriquePatient;

@Component({
  selector: 'jhi-tab-patient-update',
  templateUrl: './tab-patient-update.component.html',
})
export class TabPatientUpdateComponent implements OnInit {
  isSaving = false;
  tabadministratifs: ITabAdministratif[] = [];
  tabhistoriquepatients: ITabHistoriquePatient[] = [];

  editForm = this.fb.group({
    id: [],
    matricule: [],
    sexe: [],
    etatCivil: [],
    nombreEnfant: [],
    nombreGrossesse: [],
    nom: [],
    prenom1: [],
    prenom2: [],
    datenaissance: [],
    lieunaissance: [],
    nationalite: [],
    activite: [],
    photoUrl: [],
    matriculecreation: [],
    datecreation: [],
    matriculemodif: [],
    datemodif: [],
    tabAdministratif: [],
    tabHistoriquePatient: [],
  });

  constructor(
    protected tabPatientService: TabPatientService,
    protected tabAdministratifService: TabAdministratifService,
    protected tabHistoriquePatientService: TabHistoriquePatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabPatient }) => {
      if (!tabPatient.id) {
        const today = moment().startOf('day');
        tabPatient.datenaissance = today;
        tabPatient.datecreation = today;
        tabPatient.datemodif = today;
      }

      this.updateForm(tabPatient);

      this.tabAdministratifService
        .query({ filter: 'tabpatient-is-null' })
        .pipe(
          map((res: HttpResponse<ITabAdministratif[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabAdministratif[]) => {
          if (!tabPatient.tabAdministratif || !tabPatient.tabAdministratif.id) {
            this.tabadministratifs = resBody;
          } else {
            this.tabAdministratifService
              .find(tabPatient.tabAdministratif.id)
              .pipe(
                map((subRes: HttpResponse<ITabAdministratif>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabAdministratif[]) => (this.tabadministratifs = concatRes));
          }
        });

      this.tabHistoriquePatientService
        .query({ filter: 'tabpatient-is-null' })
        .pipe(
          map((res: HttpResponse<ITabHistoriquePatient[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabHistoriquePatient[]) => {
          if (!tabPatient.tabHistoriquePatient || !tabPatient.tabHistoriquePatient.id) {
            this.tabhistoriquepatients = resBody;
          } else {
            this.tabHistoriquePatientService
              .find(tabPatient.tabHistoriquePatient.id)
              .pipe(
                map((subRes: HttpResponse<ITabHistoriquePatient>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabHistoriquePatient[]) => (this.tabhistoriquepatients = concatRes));
          }
        });
    });
  }

  updateForm(tabPatient: ITabPatient): void {
    this.editForm.patchValue({
      id: tabPatient.id,
      matricule: tabPatient.matricule,
      sexe: tabPatient.sexe,
      etatCivil: tabPatient.etatCivil,
      nombreEnfant: tabPatient.nombreEnfant,
      nombreGrossesse: tabPatient.nombreGrossesse,
      nom: tabPatient.nom,
      prenom1: tabPatient.prenom1,
      prenom2: tabPatient.prenom2,
      datenaissance: tabPatient.datenaissance ? tabPatient.datenaissance.format(DATE_TIME_FORMAT) : null,
      lieunaissance: tabPatient.lieunaissance,
      nationalite: tabPatient.nationalite,
      activite: tabPatient.activite,
      photoUrl: tabPatient.photoUrl,
      matriculecreation: tabPatient.matriculecreation,
      datecreation: tabPatient.datecreation ? tabPatient.datecreation.format(DATE_TIME_FORMAT) : null,
      matriculemodif: tabPatient.matriculemodif,
      datemodif: tabPatient.datemodif ? tabPatient.datemodif.format(DATE_TIME_FORMAT) : null,
      tabAdministratif: tabPatient.tabAdministratif,
      tabHistoriquePatient: tabPatient.tabHistoriquePatient,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabPatient = this.createFromForm();
    if (tabPatient.id !== undefined) {
      this.subscribeToSaveResponse(this.tabPatientService.update(tabPatient));
    } else {
      this.subscribeToSaveResponse(this.tabPatientService.create(tabPatient));
    }
  }

  private createFromForm(): ITabPatient {
    return {
      ...new TabPatient(),
      id: this.editForm.get(['id'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      etatCivil: this.editForm.get(['etatCivil'])!.value,
      nombreEnfant: this.editForm.get(['nombreEnfant'])!.value,
      nombreGrossesse: this.editForm.get(['nombreGrossesse'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom1: this.editForm.get(['prenom1'])!.value,
      prenom2: this.editForm.get(['prenom2'])!.value,
      datenaissance: this.editForm.get(['datenaissance'])!.value
        ? moment(this.editForm.get(['datenaissance'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lieunaissance: this.editForm.get(['lieunaissance'])!.value,
      nationalite: this.editForm.get(['nationalite'])!.value,
      activite: this.editForm.get(['activite'])!.value,
      photoUrl: this.editForm.get(['photoUrl'])!.value,
      matriculecreation: this.editForm.get(['matriculecreation'])!.value,
      datecreation: this.editForm.get(['datecreation'])!.value
        ? moment(this.editForm.get(['datecreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculemodif: this.editForm.get(['matriculemodif'])!.value,
      datemodif: this.editForm.get(['datemodif'])!.value ? moment(this.editForm.get(['datemodif'])!.value, DATE_TIME_FORMAT) : undefined,
      tabAdministratif: this.editForm.get(['tabAdministratif'])!.value,
      tabHistoriquePatient: this.editForm.get(['tabHistoriquePatient'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabPatient>>): void {
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
