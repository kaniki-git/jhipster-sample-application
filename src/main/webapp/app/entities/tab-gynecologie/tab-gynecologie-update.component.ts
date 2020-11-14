import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabGynecologie, TabGynecologie } from 'app/shared/model/tab-gynecologie.model';
import { TabGynecologieService } from './tab-gynecologie.service';
import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';
import { TabAccouchementService } from 'app/entities/tab-accouchement/tab-accouchement.service';

@Component({
  selector: 'jhi-tab-gynecologie-update',
  templateUrl: './tab-gynecologie-update.component.html',
})
export class TabGynecologieUpdateComponent implements OnInit {
  isSaving = false;
  tabaccouchements: ITabAccouchement[] = [];

  editForm = this.fb.group({
    id: [],
    dateConsult: [],
    ddr: [],
    termeUs: [],
    termCorrige: [],
    termDdr: [],
    cycle: [],
    dateOvulation: [],
    ageGestationel: [],
    dateFin: [],
    testPeri: [],
    ecouvillon: [],
    perine: [],
    bassin: [],
    ogtt: [],
    imc: [],
    poidsMereInitial: [],
    poidsMereTheoriBebe: [],
    tailleMere: [],
    tailleTheoriBebe: [],
    gSgMari: [],
    laboTri21: [],
    caryotype: [],
    suiviPar: [],
    pediatre: [],
    risqueOrl: [],
    resumeGrossesse: [],
    conduiteAccouche: [],
    rapport: [],
    tabAccouchement: [],
  });

  constructor(
    protected tabGynecologieService: TabGynecologieService,
    protected tabAccouchementService: TabAccouchementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabGynecologie }) => {
      if (!tabGynecologie.id) {
        const today = moment().startOf('day');
        tabGynecologie.dateConsult = today;
        tabGynecologie.dateOvulation = today;
        tabGynecologie.dateFin = today;
      }

      this.updateForm(tabGynecologie);

      this.tabAccouchementService.query().subscribe((res: HttpResponse<ITabAccouchement[]>) => (this.tabaccouchements = res.body || []));
    });
  }

  updateForm(tabGynecologie: ITabGynecologie): void {
    this.editForm.patchValue({
      id: tabGynecologie.id,
      dateConsult: tabGynecologie.dateConsult ? tabGynecologie.dateConsult.format(DATE_TIME_FORMAT) : null,
      ddr: tabGynecologie.ddr,
      termeUs: tabGynecologie.termeUs,
      termCorrige: tabGynecologie.termCorrige,
      termDdr: tabGynecologie.termDdr,
      cycle: tabGynecologie.cycle,
      dateOvulation: tabGynecologie.dateOvulation ? tabGynecologie.dateOvulation.format(DATE_TIME_FORMAT) : null,
      ageGestationel: tabGynecologie.ageGestationel,
      dateFin: tabGynecologie.dateFin ? tabGynecologie.dateFin.format(DATE_TIME_FORMAT) : null,
      testPeri: tabGynecologie.testPeri,
      ecouvillon: tabGynecologie.ecouvillon,
      perine: tabGynecologie.perine,
      bassin: tabGynecologie.bassin,
      ogtt: tabGynecologie.ogtt,
      imc: tabGynecologie.imc,
      poidsMereInitial: tabGynecologie.poidsMereInitial,
      poidsMereTheoriBebe: tabGynecologie.poidsMereTheoriBebe,
      tailleMere: tabGynecologie.tailleMere,
      tailleTheoriBebe: tabGynecologie.tailleTheoriBebe,
      gSgMari: tabGynecologie.gSgMari,
      laboTri21: tabGynecologie.laboTri21,
      caryotype: tabGynecologie.caryotype,
      suiviPar: tabGynecologie.suiviPar,
      pediatre: tabGynecologie.pediatre,
      risqueOrl: tabGynecologie.risqueOrl,
      resumeGrossesse: tabGynecologie.resumeGrossesse,
      conduiteAccouche: tabGynecologie.conduiteAccouche,
      rapport: tabGynecologie.rapport,
      tabAccouchement: tabGynecologie.tabAccouchement,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabGynecologie = this.createFromForm();
    if (tabGynecologie.id !== undefined) {
      this.subscribeToSaveResponse(this.tabGynecologieService.update(tabGynecologie));
    } else {
      this.subscribeToSaveResponse(this.tabGynecologieService.create(tabGynecologie));
    }
  }

  private createFromForm(): ITabGynecologie {
    return {
      ...new TabGynecologie(),
      id: this.editForm.get(['id'])!.value,
      dateConsult: this.editForm.get(['dateConsult'])!.value
        ? moment(this.editForm.get(['dateConsult'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ddr: this.editForm.get(['ddr'])!.value,
      termeUs: this.editForm.get(['termeUs'])!.value,
      termCorrige: this.editForm.get(['termCorrige'])!.value,
      termDdr: this.editForm.get(['termDdr'])!.value,
      cycle: this.editForm.get(['cycle'])!.value,
      dateOvulation: this.editForm.get(['dateOvulation'])!.value
        ? moment(this.editForm.get(['dateOvulation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ageGestationel: this.editForm.get(['ageGestationel'])!.value,
      dateFin: this.editForm.get(['dateFin'])!.value ? moment(this.editForm.get(['dateFin'])!.value, DATE_TIME_FORMAT) : undefined,
      testPeri: this.editForm.get(['testPeri'])!.value,
      ecouvillon: this.editForm.get(['ecouvillon'])!.value,
      perine: this.editForm.get(['perine'])!.value,
      bassin: this.editForm.get(['bassin'])!.value,
      ogtt: this.editForm.get(['ogtt'])!.value,
      imc: this.editForm.get(['imc'])!.value,
      poidsMereInitial: this.editForm.get(['poidsMereInitial'])!.value,
      poidsMereTheoriBebe: this.editForm.get(['poidsMereTheoriBebe'])!.value,
      tailleMere: this.editForm.get(['tailleMere'])!.value,
      tailleTheoriBebe: this.editForm.get(['tailleTheoriBebe'])!.value,
      gSgMari: this.editForm.get(['gSgMari'])!.value,
      laboTri21: this.editForm.get(['laboTri21'])!.value,
      caryotype: this.editForm.get(['caryotype'])!.value,
      suiviPar: this.editForm.get(['suiviPar'])!.value,
      pediatre: this.editForm.get(['pediatre'])!.value,
      risqueOrl: this.editForm.get(['risqueOrl'])!.value,
      resumeGrossesse: this.editForm.get(['resumeGrossesse'])!.value,
      conduiteAccouche: this.editForm.get(['conduiteAccouche'])!.value,
      rapport: this.editForm.get(['rapport'])!.value,
      tabAccouchement: this.editForm.get(['tabAccouchement'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabGynecologie>>): void {
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

  trackById(index: number, item: ITabAccouchement): any {
    return item.id;
  }
}
