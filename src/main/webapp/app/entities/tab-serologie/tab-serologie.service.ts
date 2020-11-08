import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabSerologie } from 'app/shared/model/tab-serologie.model';

type EntityResponseType = HttpResponse<ITabSerologie>;
type EntityArrayResponseType = HttpResponse<ITabSerologie[]>;

@Injectable({ providedIn: 'root' })
export class TabSerologieService {
  public resourceUrl = SERVER_API_URL + 'api/tab-serologies';

  constructor(protected http: HttpClient) {}

  create(tabSerologie: ITabSerologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabSerologie);
    return this.http
      .post<ITabSerologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabSerologie: ITabSerologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabSerologie);
    return this.http
      .put<ITabSerologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabSerologie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabSerologie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabSerologie: ITabSerologie): ITabSerologie {
    const copy: ITabSerologie = Object.assign({}, tabSerologie, {
      dateSerologie: tabSerologie.dateSerologie && tabSerologie.dateSerologie.isValid() ? tabSerologie.dateSerologie.toJSON() : undefined,
      tarifSerologie:
        tabSerologie.tarifSerologie && tabSerologie.tarifSerologie.isValid() ? tabSerologie.tarifSerologie.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateSerologie = res.body.dateSerologie ? moment(res.body.dateSerologie) : undefined;
      res.body.tarifSerologie = res.body.tarifSerologie ? moment(res.body.tarifSerologie) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabSerologie: ITabSerologie) => {
        tabSerologie.dateSerologie = tabSerologie.dateSerologie ? moment(tabSerologie.dateSerologie) : undefined;
        tabSerologie.tarifSerologie = tabSerologie.tarifSerologie ? moment(tabSerologie.tarifSerologie) : undefined;
      });
    }
    return res;
  }
}
