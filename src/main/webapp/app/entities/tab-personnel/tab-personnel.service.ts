import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';

type EntityResponseType = HttpResponse<ITabPersonnel>;
type EntityArrayResponseType = HttpResponse<ITabPersonnel[]>;

@Injectable({ providedIn: 'root' })
export class TabPersonnelService {
  public resourceUrl = SERVER_API_URL + 'api/tab-personnels';

  constructor(protected http: HttpClient) {}

  create(tabPersonnel: ITabPersonnel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabPersonnel);
    return this.http
      .post<ITabPersonnel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabPersonnel: ITabPersonnel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabPersonnel);
    return this.http
      .put<ITabPersonnel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabPersonnel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabPersonnel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabPersonnel: ITabPersonnel): ITabPersonnel {
    const copy: ITabPersonnel = Object.assign({}, tabPersonnel, {
      dateentreeservice:
        tabPersonnel.dateentreeservice && tabPersonnel.dateentreeservice.isValid() ? tabPersonnel.dateentreeservice.toJSON() : undefined,
      datecreation: tabPersonnel.datecreation && tabPersonnel.datecreation.isValid() ? tabPersonnel.datecreation.toJSON() : undefined,
      datemodif: tabPersonnel.datemodif && tabPersonnel.datemodif.isValid() ? tabPersonnel.datemodif.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateentreeservice = res.body.dateentreeservice ? moment(res.body.dateentreeservice) : undefined;
      res.body.datecreation = res.body.datecreation ? moment(res.body.datecreation) : undefined;
      res.body.datemodif = res.body.datemodif ? moment(res.body.datemodif) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabPersonnel: ITabPersonnel) => {
        tabPersonnel.dateentreeservice = tabPersonnel.dateentreeservice ? moment(tabPersonnel.dateentreeservice) : undefined;
        tabPersonnel.datecreation = tabPersonnel.datecreation ? moment(tabPersonnel.datecreation) : undefined;
        tabPersonnel.datemodif = tabPersonnel.datemodif ? moment(tabPersonnel.datemodif) : undefined;
      });
    }
    return res;
  }
}
