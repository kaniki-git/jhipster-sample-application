import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';

type EntityResponseType = HttpResponse<ITabHospital>;
type EntityArrayResponseType = HttpResponse<ITabHospital[]>;

@Injectable({ providedIn: 'root' })
export class TabHospitalService {
  public resourceUrl = SERVER_API_URL + 'api/tab-hospitals';

  constructor(protected http: HttpClient) {}

  create(tabHospital: ITabHospital): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabHospital);
    return this.http
      .post<ITabHospital>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabHospital: ITabHospital): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabHospital);
    return this.http
      .put<ITabHospital>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabHospital>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabHospital[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabHospital: ITabHospital): ITabHospital {
    const copy: ITabHospital = Object.assign({}, tabHospital, {
      dateAdmission: tabHospital.dateAdmission && tabHospital.dateAdmission.isValid() ? tabHospital.dateAdmission.toJSON() : undefined,
      prochainRdv: tabHospital.prochainRdv && tabHospital.prochainRdv.isValid() ? tabHospital.prochainRdv.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAdmission = res.body.dateAdmission ? moment(res.body.dateAdmission) : undefined;
      res.body.prochainRdv = res.body.prochainRdv ? moment(res.body.prochainRdv) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabHospital: ITabHospital) => {
        tabHospital.dateAdmission = tabHospital.dateAdmission ? moment(tabHospital.dateAdmission) : undefined;
        tabHospital.prochainRdv = tabHospital.prochainRdv ? moment(tabHospital.prochainRdv) : undefined;
      });
    }
    return res;
  }
}
