import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabCoordonnee, TabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { TabCoordonneeService } from './tab-coordonnee.service';

@Component({
  selector: 'jhi-tab-coordonnee-update',
  templateUrl: './tab-coordonnee-update.component.html',
})
export class TabCoordonneeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    quartier: [],
    commune: [],
    ville: [],
    codeVille: [],
    rue: [],
    numero: [],
    telephone: [],
    portable: [],
    prevenir: [],
    fax: [],
    adresseMail: [],
    adressePrevenir: [],
    adresseLibelleLong: [],
    matriculecreation: [],
    datecreation: [],
    matriculemodif: [],
    datemodif: [],
  });

  constructor(protected tabCoordonneeService: TabCoordonneeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabCoordonnee }) => {
      if (!tabCoordonnee.id) {
        const today = moment().startOf('day');
        tabCoordonnee.datecreation = today;
        tabCoordonnee.datemodif = today;
      }

      this.updateForm(tabCoordonnee);
    });
  }

  updateForm(tabCoordonnee: ITabCoordonnee): void {
    this.editForm.patchValue({
      id: tabCoordonnee.id,
      quartier: tabCoordonnee.quartier,
      commune: tabCoordonnee.commune,
      ville: tabCoordonnee.ville,
      codeVille: tabCoordonnee.codeVille,
      rue: tabCoordonnee.rue,
      numero: tabCoordonnee.numero,
      telephone: tabCoordonnee.telephone,
      portable: tabCoordonnee.portable,
      prevenir: tabCoordonnee.prevenir,
      fax: tabCoordonnee.fax,
      adresseMail: tabCoordonnee.adresseMail,
      adressePrevenir: tabCoordonnee.adressePrevenir,
      adresseLibelleLong: tabCoordonnee.adresseLibelleLong,
      matriculecreation: tabCoordonnee.matriculecreation,
      datecreation: tabCoordonnee.datecreation ? tabCoordonnee.datecreation.format(DATE_TIME_FORMAT) : null,
      matriculemodif: tabCoordonnee.matriculemodif,
      datemodif: tabCoordonnee.datemodif ? tabCoordonnee.datemodif.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabCoordonnee = this.createFromForm();
    if (tabCoordonnee.id !== undefined) {
      this.subscribeToSaveResponse(this.tabCoordonneeService.update(tabCoordonnee));
    } else {
      this.subscribeToSaveResponse(this.tabCoordonneeService.create(tabCoordonnee));
    }
  }

  private createFromForm(): ITabCoordonnee {
    return {
      ...new TabCoordonnee(),
      id: this.editForm.get(['id'])!.value,
      quartier: this.editForm.get(['quartier'])!.value,
      commune: this.editForm.get(['commune'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      codeVille: this.editForm.get(['codeVille'])!.value,
      rue: this.editForm.get(['rue'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      portable: this.editForm.get(['portable'])!.value,
      prevenir: this.editForm.get(['prevenir'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      adresseMail: this.editForm.get(['adresseMail'])!.value,
      adressePrevenir: this.editForm.get(['adressePrevenir'])!.value,
      adresseLibelleLong: this.editForm.get(['adresseLibelleLong'])!.value,
      matriculecreation: this.editForm.get(['matriculecreation'])!.value,
      datecreation: this.editForm.get(['datecreation'])!.value
        ? moment(this.editForm.get(['datecreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matriculemodif: this.editForm.get(['matriculemodif'])!.value,
      datemodif: this.editForm.get(['datemodif'])!.value ? moment(this.editForm.get(['datemodif'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabCoordonnee>>): void {
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
