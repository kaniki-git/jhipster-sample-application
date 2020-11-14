import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITabUserProfil, TabUserProfil } from 'app/shared/model/tab-user-profil.model';
import { TabUserProfilService } from './tab-user-profil.service';
import { ITabUser } from 'app/shared/model/tab-user.model';
import { TabUserService } from 'app/entities/tab-user/tab-user.service';
import { ITabProfil } from 'app/shared/model/tab-profil.model';
import { TabProfilService } from 'app/entities/tab-profil/tab-profil.service';

type SelectableEntity = ITabUser | ITabProfil;

@Component({
  selector: 'jhi-tab-user-profil-update',
  templateUrl: './tab-user-profil-update.component.html',
})
export class TabUserProfilUpdateComponent implements OnInit {
  isSaving = false;
  tabusers: ITabUser[] = [];
  tabprofils: ITabProfil[] = [];

  editForm = this.fb.group({
    id: [],
    estActif: [],
    matriculeCreation: [],
    dateCreation: [],
    matriculeModif: [],
    dateModif: [],
    tabUser: [],
    tabProfil: [],
  });

  constructor(
    protected tabUserProfilService: TabUserProfilService,
    protected tabUserService: TabUserService,
    protected tabProfilService: TabProfilService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabUserProfil }) => {
      this.updateForm(tabUserProfil);

      this.tabUserService
        .query({ filter: 'tabuserprofil-is-null' })
        .pipe(
          map((res: HttpResponse<ITabUser[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabUser[]) => {
          if (!tabUserProfil.tabUser || !tabUserProfil.tabUser.id) {
            this.tabusers = resBody;
          } else {
            this.tabUserService
              .find(tabUserProfil.tabUser.id)
              .pipe(
                map((subRes: HttpResponse<ITabUser>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabUser[]) => (this.tabusers = concatRes));
          }
        });

      this.tabProfilService
        .query({ filter: 'tabuserprofil-is-null' })
        .pipe(
          map((res: HttpResponse<ITabProfil[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabProfil[]) => {
          if (!tabUserProfil.tabProfil || !tabUserProfil.tabProfil.id) {
            this.tabprofils = resBody;
          } else {
            this.tabProfilService
              .find(tabUserProfil.tabProfil.id)
              .pipe(
                map((subRes: HttpResponse<ITabProfil>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabProfil[]) => (this.tabprofils = concatRes));
          }
        });
    });
  }

  updateForm(tabUserProfil: ITabUserProfil): void {
    this.editForm.patchValue({
      id: tabUserProfil.id,
      estActif: tabUserProfil.estActif,
      matriculeCreation: tabUserProfil.matriculeCreation,
      dateCreation: tabUserProfil.dateCreation,
      matriculeModif: tabUserProfil.matriculeModif,
      dateModif: tabUserProfil.dateModif,
      tabUser: tabUserProfil.tabUser,
      tabProfil: tabUserProfil.tabProfil,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabUserProfil = this.createFromForm();
    if (tabUserProfil.id !== undefined) {
      this.subscribeToSaveResponse(this.tabUserProfilService.update(tabUserProfil));
    } else {
      this.subscribeToSaveResponse(this.tabUserProfilService.create(tabUserProfil));
    }
  }

  private createFromForm(): ITabUserProfil {
    return {
      ...new TabUserProfil(),
      id: this.editForm.get(['id'])!.value,
      estActif: this.editForm.get(['estActif'])!.value,
      matriculeCreation: this.editForm.get(['matriculeCreation'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value,
      matriculeModif: this.editForm.get(['matriculeModif'])!.value,
      dateModif: this.editForm.get(['dateModif'])!.value,
      tabUser: this.editForm.get(['tabUser'])!.value,
      tabProfil: this.editForm.get(['tabProfil'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabUserProfil>>): void {
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
