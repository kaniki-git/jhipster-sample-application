import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabAdministratif, TabAdministratif } from 'app/shared/model/tab-administratif.model';
import { TabAdministratifService } from './tab-administratif.service';
import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { TabCoordonneeService } from 'app/entities/tab-coordonnee/tab-coordonnee.service';

@Component({
  selector: 'jhi-tab-administratif-update',
  templateUrl: './tab-administratif-update.component.html',
})
export class TabAdministratifUpdateComponent implements OnInit {
  isSaving = false;
  tabcoordonnees: ITabCoordonnee[] = [];

  editForm = this.fb.group({
    id: [],
    provenancePatient: [],
    numeroChambre: [],
    batiment: [],
    dateEntree: [],
    dateSortie: [],
    commentaire: [],
    couverture: [],
    numerosecu: [],
    nomassurrance: [],
    coordonneesecu: [],
    nomMedecinPerso: [],
    matriculecreation: [],
    datecreation: [],
    matriculemodif: [],
    datemodif: [],
    tabCoordonnee: [],
  });

  constructor(
    protected tabAdministratifService: TabAdministratifService,
    protected tabCoordonneeService: TabCoordonneeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabAdministratif }) => {
      if (!tabAdministratif.id) {
        const today = moment().startOf('day');
        tabAdministratif.dateEntree = today;
        tabAdministratif.dateSortie = today;
        tabAdministratif.datecreation = today;
        tabAdministratif.datemodif = today;
      }

      this.updateForm(tabAdministratif);

      this.tabCoordonneeService
        .query({ filter: 'tabadministratif-is-null' })
        .pipe(
          map((res: HttpResponse<ITabCoordonnee[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabCoordonnee[]) => {
          if (!tabAdministratif.tabCoordonnee || !tabAdministratif.tabCoordonnee.id) {
            this.tabcoordonnees = resBody;
          } else {
            this.tabCoordonneeService
              .find(tabAdministratif.tabCoordonnee.id)
              .pipe(
                map((subRes: HttpResponse<ITabCoordonnee>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabCoordonnee[]) => (this.tabcoordonnees = concatRes));
          }
        });
    });
  }

  updateForm(tabAdministratif: ITabAdministratif): void {
    this.editForm.patchValue({
      id: tabAdministratif.id,
      provenancePatient: tabAdministratif.provenancePatient,
      numeroChambre: tabAdministratif.numeroChambre,
      batiment: tabAdministratif.batiment,
      dateEntree: tabAdministratif.dateEntree ? tabAdministratif.dateEntree.format(DATE_TIME_FORMAT) : null,
      dateSortie: tabAdministratif.dateSortie ? tabAdministratif.dateSortie.format(DATE_TIME_FORMAT) : null,
      commentaire: tabAdministratif.commentaire,
      couverture: tabAdministratif.couverture,
      numerosecu: tabAdministratif.numerosecu,
      nomassurrance: tabAdministratif.nomassurrance,
      coordonneesecu: tabAdministratif.coordonneesecu,
      nomMedecinPerso: tabAdministratif.nomMedecinPerso,
      matriculecreation: tabAdministratif.matriculecreation,
      datecreation: tabAdministratif.datecreation ? tabAdministratif.datecreation.format(DATE_TIME_FORMAT) : null,
      matriculemodif: tabAdministratif.matriculemodif,
      datemodif: tabAdministratif.datemodif ? tabAdministratif.datemodif.format(DATE_TIME_FORMAT) : null,
      tabCoordonnee: tabAdministratif.tabCoordonnee,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabAdministratif = this.createFromForm();
    if (tabAdministratif.id !== undefined) {
      this.subscribeToSaveResponse(this.tabAdministratifService.update(tabAdministratif));
    } else {
      this.subscribeToSaveResponse(this.tabAdministratifService.create(tabAdministratif));
    }
  }

  private createFromForm(): ITabAdministratif {
    return {
      ...new TabAdministratif(),
      id: this.editForm.get(['id'])!.value,
      provenancePatient: this.editForm.get(['provenancePatient'])!.value,
      numeroChambre: this.editForm.get(['numeroChambre'])!.value,
      batiment: this.editForm.get(['batiment'])!.value,
      dateEntree: this.editForm.get(['dateEntree'])!.value ? moment(this.editForm.get(['dateEntree'])!.value, DATE_TIME_FORMAT) : undefined,
      dateSortie: this.editForm.get(['dateSortie'])!.value ? moment(this.editForm.get(['dateSortie'])!.value, DATE_TIME_FORMAT) : undefined,
      commentaire: this.editForm.get(['commentaire'])!.value,
      couverture: this.editForm.get(['couverture'])!.value,
      numerosecu: this.editForm.get(['numerosecu'])!.value,
      nomassurrance: this.editForm.get(['nomassurrance'])!.value,
      coordonneesecu: this.editForm.get(['coordonneesecu'])!.value,
      nomMedecinPerso: this.editForm.get(['nomMedecinPerso'])!.value,
      matriculecreation: this.editForm.get(['matriculecreation'])!.value,
      datecreation: this.editForm.get(['datecreation'])!.value
        ? moment(this.editForm.get(['datecreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculemodif: this.editForm.get(['matriculemodif'])!.value,
      datemodif: this.editForm.get(['datemodif'])!.value ? moment(this.editForm.get(['datemodif'])!.value, DATE_TIME_FORMAT) : undefined,
      tabCoordonnee: this.editForm.get(['tabCoordonnee'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabAdministratif>>): void {
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

  trackById(index: number, item: ITabCoordonnee): any {
    return item.id;
  }
}
