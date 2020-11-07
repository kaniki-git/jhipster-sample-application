import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabGrossesse } from 'app/shared/model/tab-grossesse.model';

type EntityResponseType = HttpResponse<ITabGrossesse>;
type EntityArrayResponseType = HttpResponse<ITabGrossesse[]>;

@Injectable({ providedIn: 'root' })
export class TabGrossesseService {
  public resourceUrl = SERVER_API_URL + 'api/tab-grossesses';

  constructor(protected http: HttpClient) {}

  create(tabGrossesse: ITabGrossesse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabGrossesse);
    return this.http
      .post<ITabGrossesse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabGrossesse: ITabGrossesse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabGrossesse);
    return this.http
      .put<ITabGrossesse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabGrossesse>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabGrossesse[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabGrossesse: ITabGrossesse): ITabGrossesse {
    const copy: ITabGrossesse = Object.assign({}, tabGrossesse, {
      dateConsult: tabGrossesse.dateConsult && tabGrossesse.dateConsult.isValid() ? tabGrossesse.dateConsult.toJSON() : undefined,
      dateOvulation: tabGrossesse.dateOvulation && tabGrossesse.dateOvulation.isValid() ? tabGrossesse.dateOvulation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateConsult = res.body.dateConsult ? moment(res.body.dateConsult) : undefined;
      res.body.dateOvulation = res.body.dateOvulation ? moment(res.body.dateOvulation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabGrossesse: ITabGrossesse) => {
        tabGrossesse.dateConsult = tabGrossesse.dateConsult ? moment(tabGrossesse.dateConsult) : undefined;
        tabGrossesse.dateOvulation = tabGrossesse.dateOvulation ? moment(tabGrossesse.dateOvulation) : undefined;
      });
    }
    return res;
  }
}
