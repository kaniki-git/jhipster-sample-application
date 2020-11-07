import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabPersonnel, TabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from './tab-personnel.service';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';
import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { TabCoordonneeService } from 'app/entities/tab-coordonnee/tab-coordonnee.service';
import { ITabSpecialite } from 'app/shared/model/tab-specialite.model';
import { TabSpecialiteService } from 'app/entities/tab-specialite/tab-specialite.service';

type SelectableEntity = ITabPatient | ITabCoordonnee | ITabSpecialite;

@Component({
  selector: 'jhi-tab-personnel-update',
  templateUrl: './tab-personnel-update.component.html',
})
export class TabPersonnelUpdateComponent implements OnInit {
  isSaving = false;
  tabpatients: ITabPatient[] = [];
  tabcoordonnees: ITabCoordonnee[] = [];
  tabspecialites: ITabSpecialite[] = [];

  editForm = this.fb.group({
    id: [],
    matricule: [],
    etatCivil: [],
    typePersonnel: [],
    activite: [],
    grade: [],
    dateentreeservice: [],
    nomstatut: [],
    matriculecreation: [],
    datecreation: [],
    matriculemodif: [],
    datemodif: [],
    tabPatient: [],
    tabCoordonnee: [],
    tabSpecialite: [],
  });

  constructor(
    protected tabPersonnelService: TabPersonnelService,
    protected tabPatientService: TabPatientService,
    protected tabCoordonneeService: TabCoordonneeService,
    protected tabSpecialiteService: TabSpecialiteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabPersonnel }) => {
      if (!tabPersonnel.id) {
        const today = moment().startOf('day');
        tabPersonnel.dateentreeservice = today;
        tabPersonnel.datecreation = today;
        tabPersonnel.datemodif = today;
      }

      this.updateForm(tabPersonnel);

      this.tabPatientService
        .query({ filter: 'tabpersonnel-is-null' })
        .pipe(
          map((res: HttpResponse<ITabPatient[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabPatient[]) => {
          if (!tabPersonnel.tabPatient || !tabPersonnel.tabPatient.id) {
            this.tabpatients = resBody;
          } else {
            this.tabPatientService
              .find(tabPersonnel.tabPatient.id)
              .pipe(
                map((subRes: HttpResponse<ITabPatient>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabPatient[]) => (this.tabpatients = concatRes));
          }
        });

      this.tabCoordonneeService
        .query({ filter: 'tabpersonnel-is-null' })
        .pipe(
          map((res: HttpResponse<ITabCoordonnee[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabCoordonnee[]) => {
          if (!tabPersonnel.tabCoordonnee || !tabPersonnel.tabCoordonnee.id) {
            this.tabcoordonnees = resBody;
          } else {
            this.tabCoordonneeService
              .find(tabPersonnel.tabCoordonnee.id)
              .pipe(
                map((subRes: HttpResponse<ITabCoordonnee>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabCoordonnee[]) => (this.tabcoordonnees = concatRes));
          }
        });

      this.tabSpecialiteService.query().subscribe((res: HttpResponse<ITabSpecialite[]>) => (this.tabspecialites = res.body || []));
    });
  }

  updateForm(tabPersonnel: ITabPersonnel): void {
    this.editForm.patchValue({
      id: tabPersonnel.id,
      matricule: tabPersonnel.matricule,
      etatCivil: tabPersonnel.etatCivil,
      typePersonnel: tabPersonnel.typePersonnel,
      activite: tabPersonnel.activite,
      grade: tabPersonnel.grade,
      dateentreeservice: tabPersonnel.dateentreeservice ? tabPersonnel.dateentreeservice.format(DATE_TIME_FORMAT) : null,
      nomstatut: tabPersonnel.nomstatut,
      matriculecreation: tabPersonnel.matriculecreation,
      datecreation: tabPersonnel.datecreation ? tabPersonnel.datecreation.format(DATE_TIME_FORMAT) : null,
      matriculemodif: tabPersonnel.matriculemodif,
      datemodif: tabPersonnel.datemodif ? tabPersonnel.datemodif.format(DATE_TIME_FORMAT) : null,
      tabPatient: tabPersonnel.tabPatient,
      tabCoordonnee: tabPersonnel.tabCoordonnee,
      tabSpecialite: tabPersonnel.tabSpecialite,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabPersonnel = this.createFromForm();
    if (tabPersonnel.id !== undefined) {
      this.subscribeToSaveResponse(this.tabPersonnelService.update(tabPersonnel));
    } else {
      this.subscribeToSaveResponse(this.tabPersonnelService.create(tabPersonnel));
    }
  }

  private createFromForm(): ITabPersonnel {
    return {
      ...new TabPersonnel(),
      id: this.editForm.get(['id'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      etatCivil: this.editForm.get(['etatCivil'])!.value,
      typePersonnel: this.editForm.get(['typePersonnel'])!.value,
      activite: this.editForm.get(['activite'])!.value,
      grade: this.editForm.get(['grade'])!.value,
      dateentreeservice: this.editForm.get(['dateentreeservice'])!.value
        ? moment(this.editForm.get(['dateentreeservice'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nomstatut: this.editForm.get(['nomstatut'])!.value,
      matriculecreation: this.editForm.get(['matriculecreation'])!.value,
      datecreation: this.editForm.get(['datecreation'])!.value
        ? moment(this.editForm.get(['datecreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculemodif: this.editForm.get(['matriculemodif'])!.value,
      datemodif: this.editForm.get(['datemodif'])!.value ? moment(this.editForm.get(['datemodif'])!.value, DATE_TIME_FORMAT) : undefined,
      tabPatient: this.editForm.get(['tabPatient'])!.value,
      tabCoordonnee: this.editForm.get(['tabCoordonnee'])!.value,
      tabSpecialite: this.editForm.get(['tabSpecialite'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabPersonnel>>): void {
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
