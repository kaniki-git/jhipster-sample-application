import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';

type EntityResponseType = HttpResponse<ITabConsultation>;
type EntityArrayResponseType = HttpResponse<ITabConsultation[]>;

@Injectable({ providedIn: 'root' })
export class TabConsultationService {
  public resourceUrl = SERVER_API_URL + 'api/tab-consultations';

  constructor(protected http: HttpClient) {}

  create(tabConsultation: ITabConsultation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabConsultation);
    return this.http
      .post<ITabConsultation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabConsultation: ITabConsultation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabConsultation);
    return this.http
      .put<ITabConsultation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabConsultation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabConsultation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabConsultation: ITabConsultation): ITabConsultation {
    const copy: ITabConsultation = Object.assign({}, tabConsultation, {
      dateConsulte:
        tabConsultation.dateConsulte && tabConsultation.dateConsulte.isValid() ? tabConsultation.dateConsulte.toJSON() : undefined,
      tarifConsult:
        tabConsultation.tarifConsult && tabConsultation.tarifConsult.isValid() ? tabConsultation.tarifConsult.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateConsulte = res.body.dateConsulte ? moment(res.body.dateConsulte) : undefined;
      res.body.tarifConsult = res.body.tarifConsult ? moment(res.body.tarifConsult) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabConsultation: ITabConsultation) => {
        tabConsultation.dateConsulte = tabConsultation.dateConsulte ? moment(tabConsultation.dateConsulte) : undefined;
        tabConsultation.tarifConsult = tabConsultation.tarifConsult ? moment(tabConsultation.tarifConsult) : undefined;
      });
    }
    return res;
  }
}
