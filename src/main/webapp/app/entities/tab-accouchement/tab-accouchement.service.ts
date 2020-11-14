import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';

type EntityResponseType = HttpResponse<ITabAccouchement>;
type EntityArrayResponseType = HttpResponse<ITabAccouchement[]>;

@Injectable({ providedIn: 'root' })
export class TabAccouchementService {
  public resourceUrl = SERVER_API_URL + 'api/tab-accouchements';

  constructor(protected http: HttpClient) {}

  create(tabAccouchement: ITabAccouchement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabAccouchement);
    return this.http
      .post<ITabAccouchement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabAccouchement: ITabAccouchement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabAccouchement);
    return this.http
      .put<ITabAccouchement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabAccouchement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabAccouchement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabAccouchement: ITabAccouchement): ITabAccouchement {
    const copy: ITabAccouchement = Object.assign({}, tabAccouchement, {
      dateAccouche:
        tabAccouchement.dateAccouche && tabAccouchement.dateAccouche.isValid() ? tabAccouchement.dateAccouche.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAccouche = res.body.dateAccouche ? moment(res.body.dateAccouche) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabAccouchement: ITabAccouchement) => {
        tabAccouchement.dateAccouche = tabAccouchement.dateAccouche ? moment(tabAccouchement.dateAccouche) : undefined;
      });
    }
    return res;
  }
}
