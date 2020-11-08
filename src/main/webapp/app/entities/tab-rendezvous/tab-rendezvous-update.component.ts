import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabRendezvous, TabRendezvous } from 'app/shared/model/tab-rendezvous.model';
import { TabRendezvousService } from './tab-rendezvous.service';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';

type SelectableEntity = ITabPatient | ITabPersonnel;

@Component({
  selector: 'jhi-tab-rendezvous-update',
  templateUrl: './tab-rendezvous-update.component.html',
})
export class TabRendezvousUpdateComponent implements OnInit {
  isSaving = false;
  tabpatients: ITabPatient[] = [];
  tabpersonnels: ITabPersonnel[] = [];

  editForm = this.fb.group({
    id: [],
    titre: [],
    ville: [],
    dateDebut: [],
    dateFin: [],
    jourEntier: [],
    groupId: [],
    arrierePlanCouleur: [],
    texteCouleur: [],
    salle: [],
    commentaire: [],
    matriculecreation: [],
    datecreation: [],
    matriculemodif: [],
    datemodif: [],
    tabPatient: [],
    tabPersonnel: [],
  });

  constructor(
    protected tabRendezvousService: TabRendezvousService,
    protected tabPatientService: TabPatientService,
    protected tabPersonnelService: TabPersonnelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabRendezvous }) => {
      if (!tabRendezvous.id) {
        const today = moment().startOf('day');
        tabRendezvous.dateDebut = today;
        tabRendezvous.dateFin = today;
        tabRendezvous.datecreation = today;
        tabRendezvous.datemodif = today;
      }

      this.updateForm(tabRendezvous);

      this.tabPatientService.query().subscribe((res: HttpResponse<ITabPatient[]>) => (this.tabpatients = res.body || []));

      this.tabPersonnelService.query().subscribe((res: HttpResponse<ITabPersonnel[]>) => (this.tabpersonnels = res.body || []));
    });
  }

  updateForm(tabRendezvous: ITabRendezvous): void {
    this.editForm.patchValue({
      id: tabRendezvous.id,
      titre: tabRendezvous.titre,
      ville: tabRendezvous.ville,
      dateDebut: tabRendezvous.dateDebut ? tabRendezvous.dateDebut.format(DATE_TIME_FORMAT) : null,
      dateFin: tabRendezvous.dateFin ? tabRendezvous.dateFin.format(DATE_TIME_FORMAT) : null,
      jourEntier: tabRendezvous.jourEntier,
      groupId: tabRendezvous.groupId,
      arrierePlanCouleur: tabRendezvous.arrierePlanCouleur,
      texteCouleur: tabRendezvous.texteCouleur,
      salle: tabRendezvous.salle,
      commentaire: tabRendezvous.commentaire,
      matriculecreation: tabRendezvous.matriculecreation,
      datecreation: tabRendezvous.datecreation ? tabRendezvous.datecreation.format(DATE_TIME_FORMAT) : null,
      matriculemodif: tabRendezvous.matriculemodif,
      datemodif: tabRendezvous.datemodif ? tabRendezvous.datemodif.format(DATE_TIME_FORMAT) : null,
      tabPatient: tabRendezvous.tabPatient,
      tabPersonnel: tabRendezvous.tabPersonnel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabRendezvous = this.createFromForm();
    if (tabRendezvous.id !== undefined) {
      this.subscribeToSaveResponse(this.tabRendezvousService.update(tabRendezvous));
    } else {
      this.subscribeToSaveResponse(this.tabRendezvousService.create(tabRendezvous));
    }
  }

  private createFromForm(): ITabRendezvous {
    return {
      ...new TabRendezvous(),
      id: this.editForm.get(['id'])!.value,
      titre: this.editForm.get(['titre'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value ? moment(this.editForm.get(['dateDebut'])!.value, DATE_TIME_FORMAT) : undefined,
      dateFin: this.editForm.get(['dateFin'])!.value ? moment(this.editForm.get(['dateFin'])!.value, DATE_TIME_FORMAT) : undefined,
      jourEntier: this.editForm.get(['jourEntier'])!.value,
      groupId: this.editForm.get(['groupId'])!.value,
      arrierePlanCouleur: this.editForm.get(['arrierePlanCouleur'])!.value,
      texteCouleur: this.editForm.get(['texteCouleur'])!.value,
      salle: this.editForm.get(['salle'])!.value,
      commentaire: this.editForm.get(['commentaire'])!.value,
      matriculecreation: this.editForm.get(['matriculecreation'])!.value,
      datecreation: this.editForm.get(['datecreation'])!.value
        ? moment(this.editForm.get(['datecreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculemodif: this.editForm.get(['matriculemodif'])!.value,
      datemodif: this.editForm.get(['datemodif'])!.value ? moment(this.editForm.get(['datemodif'])!.value, DATE_TIME_FORMAT) : undefined,
      tabPatient: this.editForm.get(['tabPatient'])!.value,
      tabPersonnel: this.editForm.get(['tabPersonnel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabRendezvous>>): void {
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
