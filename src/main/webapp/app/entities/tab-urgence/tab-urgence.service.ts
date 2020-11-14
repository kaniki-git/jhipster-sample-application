import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabUrgence } from 'app/shared/model/tab-urgence.model';

type EntityResponseType = HttpResponse<ITabUrgence>;
type EntityArrayResponseType = HttpResponse<ITabUrgence[]>;

@Injectable({ providedIn: 'root' })
export class TabUrgenceService {
  public resourceUrl = SERVER_API_URL + 'api/tab-urgences';

  constructor(protected http: HttpClient) {}

  create(tabUrgence: ITabUrgence): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabUrgence);
    return this.http
      .post<ITabUrgence>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabUrgence: ITabUrgence): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabUrgence);
    return this.http
      .put<ITabUrgence>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabUrgence>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabUrgence[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabUrgence: ITabUrgence): ITabUrgence {
    const copy: ITabUrgence = Object.assign({}, tabUrgence, {
      dateArriveeUrgence:
        tabUrgence.dateArriveeUrgence && tabUrgence.dateArriveeUrgence.isValid() ? tabUrgence.dateArriveeUrgence.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateArriveeUrgence = res.body.dateArriveeUrgence ? moment(res.body.dateArriveeUrgence) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabUrgence: ITabUrgence) => {
        tabUrgence.dateArriveeUrgence = tabUrgence.dateArriveeUrgence ? moment(tabUrgence.dateArriveeUrgence) : undefined;
      });
    }
    return res;
  }
}
