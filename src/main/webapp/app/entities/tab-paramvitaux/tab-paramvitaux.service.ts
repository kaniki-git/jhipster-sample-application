import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';

type EntityResponseType = HttpResponse<ITabParamvitaux>;
type EntityArrayResponseType = HttpResponse<ITabParamvitaux[]>;

@Injectable({ providedIn: 'root' })
export class TabParamvitauxService {
  public resourceUrl = SERVER_API_URL + 'api/tab-paramvitauxes';

  constructor(protected http: HttpClient) {}

  create(tabParamvitaux: ITabParamvitaux): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabParamvitaux);
    return this.http
      .post<ITabParamvitaux>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabParamvitaux: ITabParamvitaux): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabParamvitaux);
    return this.http
      .put<ITabParamvitaux>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabParamvitaux>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabParamvitaux[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabParamvitaux: ITabParamvitaux): ITabParamvitaux {
    const copy: ITabParamvitaux = Object.assign({}, tabParamvitaux, {
      dateExam: tabParamvitaux.dateExam && tabParamvitaux.dateExam.isValid() ? tabParamvitaux.dateExam.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateExam = res.body.dateExam ? moment(res.body.dateExam) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabParamvitaux: ITabParamvitaux) => {
        tabParamvitaux.dateExam = tabParamvitaux.dateExam ? moment(tabParamvitaux.dateExam) : undefined;
      });
    }
    return res;
  }
}
