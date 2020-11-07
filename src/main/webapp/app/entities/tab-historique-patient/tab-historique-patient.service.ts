import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';

type EntityResponseType = HttpResponse<ITabHistoriquePatient>;
type EntityArrayResponseType = HttpResponse<ITabHistoriquePatient[]>;

@Injectable({ providedIn: 'root' })
export class TabHistoriquePatientService {
  public resourceUrl = SERVER_API_URL + 'api/tab-historique-patients';

  constructor(protected http: HttpClient) {}

  create(tabHistoriquePatient: ITabHistoriquePatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabHistoriquePatient);
    return this.http
      .post<ITabHistoriquePatient>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabHistoriquePatient: ITabHistoriquePatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabHistoriquePatient);
    return this.http
      .put<ITabHistoriquePatient>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabHistoriquePatient>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabHistoriquePatient[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabHistoriquePatient: ITabHistoriquePatient): ITabHistoriquePatient {
    const copy: ITabHistoriquePatient = Object.assign({}, tabHistoriquePatient, {
      datecreation:
        tabHistoriquePatient.datecreation && tabHistoriquePatient.datecreation.isValid()
          ? tabHistoriquePatient.datecreation.toJSON()
          : undefined,
      datemodif:
        tabHistoriquePatient.datemodif && tabHistoriquePatient.datemodif.isValid() ? tabHistoriquePatient.datemodif.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datecreation = res.body.datecreation ? moment(res.body.datecreation) : undefined;
      res.body.datemodif = res.body.datemodif ? moment(res.body.datemodif) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabHistoriquePatient: ITabHistoriquePatient) => {
        tabHistoriquePatient.datecreation = tabHistoriquePatient.datecreation ? moment(tabHistoriquePatient.datecreation) : undefined;
        tabHistoriquePatient.datemodif = tabHistoriquePatient.datemodif ? moment(tabHistoriquePatient.datemodif) : undefined;
      });
    }
    return res;
  }
}
