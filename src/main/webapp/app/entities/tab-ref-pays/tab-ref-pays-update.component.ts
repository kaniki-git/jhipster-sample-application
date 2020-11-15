import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITabRefPays, TabRefPays } from 'app/shared/model/tab-ref-pays.model';
import { TabRefPaysService } from './tab-ref-pays.service';

@Component({
  selector: 'jhi-tab-ref-pays-update',
  templateUrl: './tab-ref-pays-update.component.html',
})
export class TabRefPaysUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idPays: [],
    libelle: [],
  });

  constructor(protected tabRefPaysService: TabRefPaysService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabRefPays }) => {
      this.updateForm(tabRefPays);
    });
  }

  updateForm(tabRefPays: ITabRefPays): void {
    this.editForm.patchValue({
      id: tabRefPays.id,
      idPays: tabRefPays.idPays,
      libelle: tabRefPays.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabRefPays = this.createFromForm();
    if (tabRefPays.id !== undefined) {
      this.subscribeToSaveResponse(this.tabRefPaysService.update(tabRefPays));
    } else {
      this.subscribeToSaveResponse(this.tabRefPaysService.create(tabRefPays));
    }
  }

  private createFromForm(): ITabRefPays {
    return {
      ...new TabRefPays(),
      id: this.editForm.get(['id'])!.value,
      idPays: this.editForm.get(['idPays'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabRefPays>>): void {
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
