import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabBiologie, TabBiologie } from 'app/shared/model/tab-biologie.model';
import { TabBiologieService } from './tab-biologie.service';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';

type SelectableEntity = ITabConsultation | ITabGynecologie;

@Component({
  selector: 'jhi-tab-biologie-update',
  templateUrl: './tab-biologie-update.component.html',
})
export class TabBiologieUpdateComponent implements OnInit {
  isSaving = false;
  tabconsultations: ITabConsultation[] = [];
  tabgynecologies: ITabGynecologie[] = [];

  editForm = this.fb.group({
    id: [],
    dateBiologie: [],
    nomBiologie: [],
    nomSerologie: [],
    gSanguin: [],
    grSangGeni: [],
    caryotype: [],
    tarifBiologie: [],
    autresBiologie: [],
    tabConsultation: [],
    tabGynecologie: [],
  });

  constructor(
    protected tabBiologieService: TabBiologieService,
    protected tabConsultationService: TabConsultationService,
    protected tabGynecologieService: TabGynecologieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabBiologie }) => {
      if (!tabBiologie.id) {
        const today = moment().startOf('day');
        tabBiologie.dateBiologie = today;
      }

      this.updateForm(tabBiologie);

      this.tabConsultationService.query().subscribe((res: HttpResponse<ITabConsultation[]>) => (this.tabconsultations = res.body || []));

      this.tabGynecologieService.query().subscribe((res: HttpResponse<ITabGynecologie[]>) => (this.tabgynecologies = res.body || []));
    });
  }

  updateForm(tabBiologie: ITabBiologie): void {
    this.editForm.patchValue({
      id: tabBiologie.id,
      dateBiologie: tabBiologie.dateBiologie ? tabBiologie.dateBiologie.format(DATE_TIME_FORMAT) : null,
      nomBiologie: tabBiologie.nomBiologie,
      nomSerologie: tabBiologie.nomSerologie,
      gSanguin: tabBiologie.gSanguin,
      grSangGeni: tabBiologie.grSangGeni,
      caryotype: tabBiologie.caryotype,
      tarifBiologie: tabBiologie.tarifBiologie,
      autresBiologie: tabBiologie.autresBiologie,
      tabConsultation: tabBiologie.tabConsultation,
      tabGynecologie: tabBiologie.tabGynecologie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabBiologie = this.createFromForm();
    if (tabBiologie.id !== undefined) {
      this.subscribeToSaveResponse(this.tabBiologieService.update(tabBiologie));
    } else {
      this.subscribeToSaveResponse(this.tabBiologieService.create(tabBiologie));
    }
  }

  private createFromForm(): ITabBiologie {
    return {
      ...new TabBiologie(),
      id: this.editForm.get(['id'])!.value,
      dateBiologie: this.editForm.get(['dateBiologie'])!.value
        ? moment(this.editForm.get(['dateBiologie'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nomBiologie: this.editForm.get(['nomBiologie'])!.value,
      nomSerologie: this.editForm.get(['nomSerologie'])!.value,
      gSanguin: this.editForm.get(['gSanguin'])!.value,
      grSangGeni: this.editForm.get(['grSangGeni'])!.value,
      caryotype: this.editForm.get(['caryotype'])!.value,
      tarifBiologie: this.editForm.get(['tarifBiologie'])!.value,
      autresBiologie: this.editForm.get(['autresBiologie'])!.value,
      tabConsultation: this.editForm.get(['tabConsultation'])!.value,
      tabGynecologie: this.editForm.get(['tabGynecologie'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabBiologie>>): void {
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
