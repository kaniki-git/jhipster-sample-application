import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabHospital, TabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from './tab-hospital.service';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';

@Component({
  selector: 'jhi-tab-hospital-update',
  templateUrl: './tab-hospital-update.component.html',
})
export class TabHospitalUpdateComponent implements OnInit {
  isSaving = false;
  tabpersonnels: ITabPersonnel[] = [];

  editForm = this.fb.group({
    id: [],
    dateAdmission: [],
    motifAdmission: [],
    provenancePatient: [],
    evolJour: [],
    evolSynthese: [],
    plantTherapeute: [],
    ledevenirPatient: [],
    prochainRdv: [],
    lieuRdv: [],
    conclusionSejour: [],
    nomConsultant: [],
    bilanAdmission: [],
    rapport: [],
    tabPersonnels: [],
  });

  constructor(
    protected tabHospitalService: TabHospitalService,
    protected tabPersonnelService: TabPersonnelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabHospital }) => {
      if (!tabHospital.id) {
        const today = moment().startOf('day');
        tabHospital.dateAdmission = today;
        tabHospital.prochainRdv = today;
      }

      this.updateForm(tabHospital);

      this.tabPersonnelService.query().subscribe((res: HttpResponse<ITabPersonnel[]>) => (this.tabpersonnels = res.body || []));
    });
  }

  updateForm(tabHospital: ITabHospital): void {
    this.editForm.patchValue({
      id: tabHospital.id,
      dateAdmission: tabHospital.dateAdmission ? tabHospital.dateAdmission.format(DATE_TIME_FORMAT) : null,
      motifAdmission: tabHospital.motifAdmission,
      provenancePatient: tabHospital.provenancePatient,
      evolJour: tabHospital.evolJour,
      evolSynthese: tabHospital.evolSynthese,
      plantTherapeute: tabHospital.plantTherapeute,
      ledevenirPatient: tabHospital.ledevenirPatient,
      prochainRdv: tabHospital.prochainRdv ? tabHospital.prochainRdv.format(DATE_TIME_FORMAT) : null,
      lieuRdv: tabHospital.lieuRdv,
      conclusionSejour: tabHospital.conclusionSejour,
      nomConsultant: tabHospital.nomConsultant,
      bilanAdmission: tabHospital.bilanAdmission,
      rapport: tabHospital.rapport,
      tabPersonnels: tabHospital.tabPersonnels,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabHospital = this.createFromForm();
    if (tabHospital.id !== undefined) {
      this.subscribeToSaveResponse(this.tabHospitalService.update(tabHospital));
    } else {
      this.subscribeToSaveResponse(this.tabHospitalService.create(tabHospital));
    }
  }

  private createFromForm(): ITabHospital {
    return {
      ...new TabHospital(),
      id: this.editForm.get(['id'])!.value,
      dateAdmission: this.editForm.get(['dateAdmission'])!.value
        ? moment(this.editForm.get(['dateAdmission'])!.value, DATE_TIME_FORMAT)
        : undefined,
      motifAdmission: this.editForm.get(['motifAdmission'])!.value,
      provenancePatient: this.editForm.get(['provenancePatient'])!.value,
      evolJour: this.editForm.get(['evolJour'])!.value,
      evolSynthese: this.editForm.get(['evolSynthese'])!.value,
      plantTherapeute: this.editForm.get(['plantTherapeute'])!.value,
      ledevenirPatient: this.editForm.get(['ledevenirPatient'])!.value,
      prochainRdv: this.editForm.get(['prochainRdv'])!.value
        ? moment(this.editForm.get(['prochainRdv'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lieuRdv: this.editForm.get(['lieuRdv'])!.value,
      conclusionSejour: this.editForm.get(['conclusionSejour'])!.value,
      nomConsultant: this.editForm.get(['nomConsultant'])!.value,
      bilanAdmission: this.editForm.get(['bilanAdmission'])!.value,
      rapport: this.editForm.get(['rapport'])!.value,
      tabPersonnels: this.editForm.get(['tabPersonnels'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabHospital>>): void {
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

  getSelected(selectedVals: ITabPersonnel[], option: ITabPersonnel): ITabPersonnel {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
