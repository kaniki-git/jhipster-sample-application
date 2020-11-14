import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITabSpecialite, TabSpecialite } from 'app/shared/model/tab-specialite.model';
import { TabSpecialiteService } from './tab-specialite.service';

@Component({
  selector: 'jhi-tab-specialite-update',
  templateUrl: './tab-specialite-update.component.html',
})
export class TabSpecialiteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
    nomPersonnel: [],
  });

  constructor(protected tabSpecialiteService: TabSpecialiteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabSpecialite }) => {
      this.updateForm(tabSpecialite);
    });
  }

  updateForm(tabSpecialite: ITabSpecialite): void {
    this.editForm.patchValue({
      id: tabSpecialite.id,
      libelle: tabSpecialite.libelle,
      nomPersonnel: tabSpecialite.nomPersonnel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabSpecialite = this.createFromForm();
    if (tabSpecialite.id !== undefined) {
      this.subscribeToSaveResponse(this.tabSpecialiteService.update(tabSpecialite));
    } else {
      this.subscribeToSaveResponse(this.tabSpecialiteService.create(tabSpecialite));
    }
  }

  private createFromForm(): ITabSpecialite {
    return {
      ...new TabSpecialite(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      nomPersonnel: this.editForm.get(['nomPersonnel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabSpecialite>>): void {
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
