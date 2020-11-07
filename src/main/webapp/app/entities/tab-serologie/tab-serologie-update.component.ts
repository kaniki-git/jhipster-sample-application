import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITabSerologie, TabSerologie } from 'app/shared/model/tab-serologie.model';
import { TabSerologieService } from './tab-serologie.service';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';
import { ITabGrossesse } from 'app/shared/model/tab-grossesse.model';
import { TabGrossesseService } from 'app/entities/tab-grossesse/tab-grossesse.service';

type SelectableEntity = ITabPatient | ITabGrossesse;

@Component({
  selector: 'jhi-tab-serologie-update',
  templateUrl: './tab-serologie-update.component.html',
})
export class TabSerologieUpdateComponent implements OnInit {
  isSaving = false;
  tabpatients: ITabPatient[] = [];
  tabgrossesses: ITabGrossesse[] = [];
  tabpatients: ITabPatient[] = [];

  editForm = this.fb.group({
    id: [],
    dateSerologie: [],
    grSang: [],
    grSangGeni: [],
    caryotype: [],
    tarifSerologie: [],
    autres: [],
    rapport: [],
    tabPatient: [],
    tabGrossesse: [],
    tabPatient: [],
  });

  constructor(
    protected tabSerologieService: TabSerologieService,
    protected tabPatientService: TabPatientService,
    protected tabGrossesseService: TabGrossesseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tabSerologie }) => {
      if (!tabSerologie.id) {
        const today = moment().startOf('day');
        tabSerologie.dateSerologie = today;
        tabSerologie.tarifSerologie = today;
      }

      this.updateForm(tabSerologie);

      this.tabPatientService
        .query({ filter: 'tabserologie-is-null' })
        .pipe(
          map((res: HttpResponse<ITabPatient[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITabPatient[]) => {
          if (!tabSerologie.tabPatient || !tabSerologie.tabPatient.id) {
            this.tabpatients = resBody;
          } else {
            this.tabPatientService
              .find(tabSerologie.tabPatient.id)
              .pipe(
                map((subRes: HttpResponse<ITabPatient>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITabPatient[]) => (this.tabpatients = concatRes));
          }
        });

      this.tabGrossesseService.query().subscribe((res: HttpResponse<ITabGrossesse[]>) => (this.tabgrossesses = res.body || []));

      this.tabPatientService.query().subscribe((res: HttpResponse<ITabPatient[]>) => (this.tabpatients = res.body || []));
    });
  }

  updateForm(tabSerologie: ITabSerologie): void {
    this.editForm.patchValue({
      id: tabSerologie.id,
      dateSerologie: tabSerologie.dateSerologie ? tabSerologie.dateSerologie.format(DATE_TIME_FORMAT) : null,
      grSang: tabSerologie.grSang,
      grSangGeni: tabSerologie.grSangGeni,
      caryotype: tabSerologie.caryotype,
      tarifSerologie: tabSerologie.tarifSerologie ? tabSerologie.tarifSerologie.format(DATE_TIME_FORMAT) : null,
      autres: tabSerologie.autres,
      rapport: tabSerologie.rapport,
      tabPatient: tabSerologie.tabPatient,
      tabGrossesse: tabSerologie.tabGrossesse,
      tabPatient: tabSerologie.tabPatient,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tabSerologie = this.createFromForm();
    if (tabSerologie.id !== undefined) {
      this.subscribeToSaveResponse(this.tabSerologieService.update(tabSerologie));
    } else {
      this.subscribeToSaveResponse(this.tabSerologieService.create(tabSerologie));
    }
  }

  private createFromForm(): ITabSerologie {
    return {
      ...new TabSerologie(),
      id: this.editForm.get(['id'])!.value,
      dateSerologie: this.editForm.get(['dateSerologie'])!.value
        ? moment(this.editForm.get(['dateSerologie'])!.value, DATE_TIME_FORMAT)
        : undefined,
      grSang: this.editForm.get(['grSang'])!.value,
      grSangGeni: this.editForm.get(['grSangGeni'])!.value,
      caryotype: this.editForm.get(['caryotype'])!.value,
      tarifSerologie: this.editForm.get(['tarifSerologie'])!.value
        ? moment(this.editForm.get(['tarifSerologie'])!.value, DATE_TIME_FORMAT)
        : undefined,
      autres: this.editForm.get(['autres'])!.value,
      rapport: this.editForm.get(['rapport'])!.value,
      tabPatient: this.editForm.get(['tabPatient'])!.value,
      tabGrossesse: this.editForm.get(['tabGrossesse'])!.value,
      tabPatient: this.editForm.get(['tabPatient'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITabSerologie>>): void {
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
