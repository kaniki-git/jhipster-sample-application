import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITabProfil, TabProfil } from 'app/shared/model/tab-profil.model';
import { TabProfilService } from './tab-profil.service';

@Component({
  selector: 'jhi-tab-profil-update',
  templateUrl: './tab-profil-update.component.html',
})
export class TabProfilUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
    estActif: [],
  });

  constructor(protected tabProfilService: TabProfilService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabProfil }) => {
      this.updateForm(tabProfil);
    });
  }

  updateForm(tabProfil: ITabProfil): void {
    this.editForm.patchValue({
      id: tabProfil.id,
      libelle: tabProfil.libelle,
      estActif: tabProfil.estActif,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabProfil = this.createFromForm();
    if (tabProfil.id !== undefined) {
      this.subscribeToSaveResponse(this.tabProfilService.update(tabProfil));
    } else {
      this.subscribeToSaveResponse(this.tabProfilService.create(tabProfil));
    }
  }

  private createFromForm(): ITabProfil {
    return {
      ...new TabProfil(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      estActif: this.editForm.get(['estActif'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabProfil>>): void {
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
}
