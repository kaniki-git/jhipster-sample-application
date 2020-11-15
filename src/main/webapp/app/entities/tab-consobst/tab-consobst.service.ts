import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabConsobst } from 'app/shared/model/tab-consobst.model';

type EntityResponseType = HttpResponse<ITabConsobst>;
type EntityArrayResponseType = HttpResponse<ITabConsobst[]>;

@Injectable({ providedIn: 'root' })
export class TabConsobstService {
  public resourceUrl = SERVER_API_URL + 'api/tab-consobsts';

  constructor(protected http: HttpClient) {}

  create(tabConsobst: ITabConsobst): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabConsobst);
    return this.http
      .post<ITabConsobst>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabConsobst: ITabConsobst): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabConsobst);
    return this.http
      .put<ITabConsobst>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabConsobst>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabConsobst[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabConsobst: ITabConsobst): ITabConsobst {
    const copy: ITabConsobst = Object.assign({}, tabConsobst, {
      dateConsObst: tabConsobst.dateConsObst && tabConsobst.dateConsObst.isValid() ? tabConsobst.dateConsObst.toJSON() : undefined,
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
      res.body.forEach((tabConsobst: ITabConsobst) => {
        tabConsobst.dateConsObst = tabConsobst.dateConsObst ? moment(tabConsobst.dateConsObst) : undefined;
      });
    }
    return res;
  }
}
