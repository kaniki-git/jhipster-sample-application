import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITabComptabilite, TabComptabilite } from 'app/shared/model/tab-comptabilite.model';
import { TabComptabiliteService } from './tab-comptabilite.service';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';

@Component({
  selector: 'jhi-tab-comptabilite-update',
  templateUrl: './tab-comptabilite-update.component.html',
})
export class TabComptabiliteUpdateComponent implements OnInit {
  isSaving = false;
  tabpatients: ITabPatient[] = [];

  editForm = this.fb.group({
    id: [],
    nomAppareil: [],
    tarifAppareil: [],
    tarifSpecialite: [],
    tarifConsultation: [],
    tarifChambre: [],
    tarifSejour: [],
    facturePatient: [],
    recette: [],
    tabPatient: [],
  });

  constructor(
    protected tabComptabiliteService: TabComptabiliteService,
    protected tabPatientService: TabPatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabComptabilite }) => {
      this.updateForm(tabComptabilite);

      this.tabPatientService.query().subscribe((res: HttpResponse<ITabPatient[]>) => (this.tabpatients = res.body || []));
    });
  }

  updateForm(tabComptabilite: ITabComptabilite): void {
    this.editForm.patchValue({
      id: tabComptabilite.id,
      nomAppareil: tabComptabilite.nomAppareil,
      tarifAppareil: tabComptabilite.tarifAppareil,
      tarifSpecialite: tabComptabilite.tarifSpecialite,
      tarifConsultation: tabComptabilite.tarifConsultation,
      tarifChambre: tabComptabilite.tarifChambre,
      tarifSejour: tabComptabilite.tarifSejour,
      facturePatient: tabComptabilite.facturePatient,
      recette: tabComptabilite.recette,
      tabPatient: tabComptabilite.tabPatient,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabComptabilite = this.createFromForm();
    if (tabComptabilite.id !== undefined) {
      this.subscribeToSaveResponse(this.tabComptabiliteService.update(tabComptabilite));
    } else {
      this.subscribeToSaveResponse(this.tabComptabiliteService.create(tabComptabilite));
    }
  }

  private createFromForm(): ITabComptabilite {
    return {
      ...new TabComptabilite(),
      id: this.editForm.get(['id'])!.value,
      nomAppareil: this.editForm.get(['nomAppareil'])!.value,
      tarifAppareil: this.editForm.get(['tarifAppareil'])!.value,
      tarifSpecialite: this.editForm.get(['tarifSpecialite'])!.value,
      tarifConsultation: this.editForm.get(['tarifConsultation'])!.value,
      tarifChambre: this.editForm.get(['tarifChambre'])!.value,
      tarifSejour: this.editForm.get(['tarifSejour'])!.value,
      facturePatient: this.editForm.get(['facturePatient'])!.value,
      recette: this.editForm.get(['recette'])!.value,
      tabPatient: this.editForm.get(['tabPatient'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabComptabilite>>): void {
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
