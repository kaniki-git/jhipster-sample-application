import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

type EntityResponseType = HttpResponse<ITabCoordonnee>;
type EntityArrayResponseType = HttpResponse<ITabCoordonnee[]>;

@Injectable({ providedIn: 'root' })
export class TabCoordonneeService {
  public resourceUrl = SERVER_API_URL + 'api/tab-coordonnees';

  constructor(protected http: HttpClient) {}

  create(tabCoordonnee: ITabCoordonnee): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabCoordonnee);
    return this.http
      .post<ITabCoordonnee>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabCoordonnee: ITabCoordonnee): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabCoordonnee);
    return this.http
      .put<ITabCoordonnee>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabCoordonnee>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabCoordonnee[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabCoordonnee: ITabCoordonnee): ITabCoordonnee {
    const copy: ITabCoordonnee = Object.assign({}, tabCoordonnee, {
      datecreation: tabCoordonnee.datecreation && tabCoordonnee.datecreation.isValid() ? tabCoordonnee.datecreation.toJSON() : undefined,
      datemodif: tabCoordonnee.datemodif && tabCoordonnee.datemodif.isValid() ? tabCoordonnee.datemodif.toJSON() : undefined,
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
      res.body.forEach((tabCoordonnee: ITabCoordonnee) => {
        tabCoordonnee.datecreation = tabCoordonnee.datecreation ? moment(tabCoordonnee.datecreation) : undefined;
        tabCoordonnee.datemodif = tabCoordonnee.datemodif ? moment(tabCoordonnee.datemodif) : undefined;
      });
    }
    return res;
  }
}
