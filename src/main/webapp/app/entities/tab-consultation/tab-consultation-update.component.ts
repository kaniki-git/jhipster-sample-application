import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabConsultation, TabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from './tab-consultation.service';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';
import { TabHospitalService } from 'app/entities/tab-hospital/tab-hospital.service';

type SelectableEntity = ITabPersonnel | ITabHospital;

@Component({
  selector: 'jhi-tab-consultation-update',
  templateUrl: './tab-consultation-update.component.html',
})
export class TabConsultationUpdateComponent implements OnInit {
  isSaving = false;
  tabpersonnels: ITabPersonnel[] = [];
  tabhospitals: ITabHospital[] = [];

  editForm = this.fb.group({
    id: [],
    dateConsulte: [],
    provenancePatient: [],
    motif: [],
    affectActuel: [],
    antecedent: [],
    traiteHabituel: [],
    allergie: [],
    tabac: [],
    alcool: [],
    facteurRisque: [],
    hypotheseDiag: [],
    avisMedical: [],
    ordreMedical: [],
    traitePropose: [],
    ledevenirPatient: [],
    tarifConsult: [],
    rapport: [],
    tabPersonnels: [],
    tabHospital: [],
  });

  constructor(
    protected tabConsultationService: TabConsultationService,
    protected tabPersonnelService: TabPersonnelService,
    protected tabHospitalService: TabHospitalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabConsultation }) => {
      if (!tabConsultation.id) {
        const today = moment().startOf('day');
        tabConsultation.dateConsulte = today;
        tabConsultation.tarifConsult = today;
      }

      this.updateForm(tabConsultation);

      this.tabPersonnelService.query().subscribe((res: HttpResponse<ITabPersonnel[]>) => (this.tabpersonnels = res.body || []));

      this.tabHospitalService.query().subscribe((res: HttpResponse<ITabHospital[]>) => (this.tabhospitals = res.body || []));
    });
  }

  updateForm(tabConsultation: ITabConsultation): void {
    this.editForm.patchValue({
      id: tabConsultation.id,
      dateConsulte: tabConsultation.dateConsulte ? tabConsultation.dateConsulte.format(DATE_TIME_FORMAT) : null,
      provenancePatient: tabConsultation.provenancePatient,
      motif: tabConsultation.motif,
      affectActuel: tabConsultation.affectActuel,
      antecedent: tabConsultation.antecedent,
      traiteHabituel: tabConsultation.traiteHabituel,
      allergie: tabConsultation.allergie,
      tabac: tabConsultation.tabac,
      alcool: tabConsultation.alcool,
      facteurRisque: tabConsultation.facteurRisque,
      hypotheseDiag: tabConsultation.hypotheseDiag,
      avisMedical: tabConsultation.avisMedical,
      ordreMedical: tabConsultation.ordreMedical,
      traitePropose: tabConsultation.traitePropose,
      ledevenirPatient: tabConsultation.ledevenirPatient,
      tarifConsult: tabConsultation.tarifConsult ? tabConsultation.tarifConsult.format(DATE_TIME_FORMAT) : null,
      rapport: tabConsultation.rapport,
      tabPersonnels: tabConsultation.tabPersonnels,
      tabHospital: tabConsultation.tabHospital,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabConsultation = this.createFromForm();
    if (tabConsultation.id !== undefined) {
      this.subscribeToSaveResponse(this.tabConsultationService.update(tabConsultation));
    } else {
      this.subscribeToSaveResponse(this.tabConsultationService.create(tabConsultation));
    }
  }

  private createFromForm(): ITabConsultation {
    return {
      ...new TabConsultation(),
      id: this.editForm.get(['id'])!.value,
      dateConsulte: this.editForm.get(['dateConsulte'])!.value
        ? moment(this.editForm.get(['dateConsulte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      provenancePatient: this.editForm.get(['provenancePatient'])!.value,
      motif: this.editForm.get(['motif'])!.value,
      affectActuel: this.editForm.get(['affectActuel'])!.value,
      antecedent: this.editForm.get(['antecedent'])!.value,
      traiteHabituel: this.editForm.get(['traiteHabituel'])!.value,
      allergie: this.editForm.get(['allergie'])!.value,
      tabac: this.editForm.get(['tabac'])!.value,
      alcool: this.editForm.get(['alcool'])!.value,
      facteurRisque: this.editForm.get(['facteurRisque'])!.value,
      hypotheseDiag: this.editForm.get(['hypotheseDiag'])!.value,
      avisMedical: this.editForm.get(['avisMedical'])!.value,
      ordreMedical: this.editForm.get(['ordreMedical'])!.value,
      traitePropose: this.editForm.get(['traitePropose'])!.value,
      ledevenirPatient: this.editForm.get(['ledevenirPatient'])!.value,
      tarifConsult: this.editForm.get(['tarifConsult'])!.value
        ? moment(this.editForm.get(['tarifConsult'])!.value, DATE_TIME_FORMAT)
        : undefined,
      rapport: this.editForm.get(['rapport'])!.value,
      tabPersonnels: this.editForm.get(['tabPersonnels'])!.value,
      tabHospital: this.editForm.get(['tabHospital'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabConsultation>>): void {
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
