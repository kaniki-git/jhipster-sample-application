import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITabRefFonction, TabRefFonction } from 'app/shared/model/tab-ref-fonction.model';
import { TabRefFonctionService } from './tab-ref-fonction.service';

@Component({
  selector: 'jhi-tab-ref-fonction-update',
  templateUrl: './tab-ref-fonction-update.component.html',
})
export class TabRefFonctionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idFonction: [],
    libelle: [],
  });

  constructor(protected tabRefFonctionService: TabRefFonctionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabRefFonction }) => {
      this.updateForm(tabRefFonction);
    });
  }

  updateForm(tabRefFonction: ITabRefFonction): void {
    this.editForm.patchValue({
      id: tabRefFonction.id,
      idFonction: tabRefFonction.idFonction,
      libelle: tabRefFonction.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabRefFonction = this.createFromForm();
    if (tabRefFonction.id !== undefined) {
      this.subscribeToSaveResponse(this.tabRefFonctionService.update(tabRefFonction));
    } else {
      this.subscribeToSaveResponse(this.tabRefFonctionService.create(tabRefFonction));
    }
  }

  private createFromForm(): ITabRefFonction {
    return {
      ...new TabRefFonction(),
      id: this.editForm.get(['id'])!.value,
      idFonction: this.editForm.get(['idFonction'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabRefFonction>>): void {
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
