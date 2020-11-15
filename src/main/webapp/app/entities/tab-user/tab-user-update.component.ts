import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabUser, TabUser } from 'app/shared/model/tab-user.model';
import { TabUserService } from './tab-user.service';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';

@Component({
  selector: 'jhi-tab-user-update',
  templateUrl: './tab-user-update.component.html',
})
export class TabUserUpdateComponent implements OnInit {
  isSaving = false;
  tabpersonnels: ITabPersonnel[] = [];

  editForm = this.fb.group({
    id: [],
    userId: [],
    login: [],
    mdp: [],
    nom: [],
    prenom: [],
    dateNaissance: [],
    genre: [],
    sexe: [],
    telephone: [],
    email: [],
    estActif: [],
    dMajMdp: [],
    staConnex: [],
    matriculeCreation: [],
    matriculeModif: [],
    dateCreation: [],
    dateModif: [],
    langue: [],
    resetKey: [],
    resetDate: [],
    tabPersonnel: [],
  });

  constructor(
    protected tabUserService: TabUserService,
    protected tabPersonnelService: TabPersonnelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUser }) => {
      if (!tabUser.id) {
        const today = moment().startOf('day');
        tabUser.resetDate = today;
      }

      this.updateForm(tabUser);

      this.tabPersonnelService
        .query({ filter: 'tabuser-is-null' })
        .pipe(
          map((res: HttpResponse<ITabPersonnel[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabPersonnel[]) => {
          if (!tabUser.tabPersonnel || !tabUser.tabPersonnel.id) {
            this.tabpersonnels = resBody;
          } else {
            this.tabPersonnelService
              .find(tabUser.tabPersonnel.id)
              .pipe(
                map((subRes: HttpResponse<ITabPersonnel>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabPersonnel[]) => (this.tabpersonnels = concatRes));
          }
        });
    });
  }

  updateForm(tabUser: ITabUser): void {
    this.editForm.patchValue({
      id: tabUser.id,
      userId: tabUser.userId,
      login: tabUser.login,
      mdp: tabUser.mdp,
      nom: tabUser.nom,
      prenom: tabUser.prenom,
      dateNaissance: tabUser.dateNaissance,
      genre: tabUser.genre,
      sexe: tabUser.sexe,
      telephone: tabUser.telephone,
      email: tabUser.email,
      estActif: tabUser.estActif,
      dMajMdp: tabUser.dMajMdp,
      staConnex: tabUser.staConnex,
      matriculeCreation: tabUser.matriculeCreation,
      matriculeModif: tabUser.matriculeModif,
      dateCreation: tabUser.dateCreation,
      dateModif: tabUser.dateModif,
      langue: tabUser.langue,
      resetKey: tabUser.resetKey,
      resetDate: tabUser.resetDate ? tabUser.resetDate.format(DATE_TIME_FORMAT) : null,
      tabPersonnel: tabUser.tabPersonnel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabUser = this.createFromForm();
    if (tabUser.id !== undefined) {
      this.subscribeToSaveResponse(this.tabUserService.update(tabUser));
    } else {
      this.subscribeToSaveResponse(this.tabUserService.create(tabUser));
    }
  }

  private createFromForm(): ITabUser {
    return {
      ...new TabUser(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      login: this.editForm.get(['login'])!.value,
      mdp: this.editForm.get(['mdp'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      genre: this.editForm.get(['genre'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      email: this.editForm.get(['email'])!.value,
      estActif: this.editForm.get(['estActif'])!.value,
      dMajMdp: this.editForm.get(['dMajMdp'])!.value,
      staConnex: this.editForm.get(['staConnex'])!.value,
      matriculeCreation: this.editForm.get(['matriculeCreation'])!.value,
      matriculeModif: this.editForm.get(['matriculeModif'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value,
      dateModif: this.editForm.get(['dateModif'])!.value,
      langue: this.editForm.get(['langue'])!.value,
      resetKey: this.editForm.get(['resetKey'])!.value,
      resetDate: this.editForm.get(['resetDate'])!.value ? moment(this.editForm.get(['resetDate'])!.value, DATE_TIME_FORMAT) : undefined,
      tabPersonnel: this.editForm.get(['tabPersonnel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabUser>>): void {
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

  trackById(index: number, item: ITabPersonnel): any {
    return item.id;
  }
}
