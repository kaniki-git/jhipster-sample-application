import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabPatient } from 'app/shared/model/tab-patient.model';

type EntityResponseType = HttpResponse<ITabPatient>;
type EntityArrayResponseType = HttpResponse<ITabPatient[]>;

@Injectable({ providedIn: 'root' })
export class TabPatientService {
  public resourceUrl = SERVER_API_URL + 'api/tab-patients';

  constructor(protected http: HttpClient) {}

  create(tabPatient: ITabPatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabPatient);
    return this.http
      .post<ITabPatient>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabPatient: ITabPatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabPatient);
    return this.http
      .put<ITabPatient>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabPatient>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabPatient[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabPatient: ITabPatient): ITabPatient {
    const copy: ITabPatient = Object.assign({}, tabPatient, {
      datenaissance: tabPatient.datenaissance && tabPatient.datenaissance.isValid() ? tabPatient.datenaissance.toJSON() : undefined,
      datecreation: tabPatient.datecreation && tabPatient.datecreation.isValid() ? tabPatient.datecreation.toJSON() : undefined,
      datemodif: tabPatient.datemodif && tabPatient.datemodif.isValid() ? tabPatient.datemodif.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datenaissance = res.body.datenaissance ? moment(res.body.datenaissance) : undefined;
      res.body.datecreation = res.body.datecreation ? moment(res.body.datecreation) : undefined;
      res.body.datemodif = res.body.datemodif ? moment(res.body.datemodif) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabPatient: ITabPatient) => {
        tabPatient.datenaissance = tabPatient.datenaissance ? moment(tabPatient.datenaissance) : undefined;
        tabPatient.datecreation = tabPatient.datecreation ? moment(tabPatient.datecreation) : undefined;
        tabPatient.datemodif = tabPatient.datemodif ? moment(tabPatient.datemodif) : undefined;
      });
    }
    return res;
  }
}
