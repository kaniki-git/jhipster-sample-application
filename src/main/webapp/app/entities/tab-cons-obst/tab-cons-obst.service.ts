import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabConsObst } from 'app/shared/model/tab-cons-obst.model';

type EntityResponseType = HttpResponse<ITabConsObst>;
type EntityArrayResponseType = HttpResponse<ITabConsObst[]>;

@Injectable({ providedIn: 'root' })
export class TabConsObstService {
  public resourceUrl = SERVER_API_URL + 'api/tab-cons-obsts';

  constructor(protected http: HttpClient) {}

  create(tabConsObst: ITabConsObst): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabConsObst);
    return this.http
      .post<ITabConsObst>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabConsObst: ITabConsObst): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabConsObst);
    return this.http
      .put<ITabConsObst>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabConsObst>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabConsObst[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabConsObst: ITabConsObst): ITabConsObst {
    const copy: ITabConsObst = Object.assign({}, tabConsObst, {
      dateConsObst: tabConsObst.dateConsObst && tabConsObst.dateConsObst.isValid() ? tabConsObst.dateConsObst.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateConsObst = res.body.dateConsObst ? moment(res.body.dateConsObst) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabConsObst: ITabConsObst) => {
        tabConsObst.dateConsObst = tabConsObst.dateConsObst ? moment(tabConsObst.dateConsObst) : undefined;
      });
    }
    return res;
  }
}
