import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';

type EntityResponseType = HttpResponse<ITabAdministratif>;
type EntityArrayResponseType = HttpResponse<ITabAdministratif[]>;

@Injectable({ providedIn: 'root' })
export class TabAdministratifService {
  public resourceUrl = SERVER_API_URL + 'api/tab-administratifs';

  constructor(protected http: HttpClient) {}

  create(tabAdministratif: ITabAdministratif): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabAdministratif);
    return this.http
      .post<ITabAdministratif>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabAdministratif: ITabAdministratif): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabAdministratif);
    return this.http
      .put<ITabAdministratif>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabAdministratif>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabAdministratif[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabAdministratif: ITabAdministratif): ITabAdministratif {
    const copy: ITabAdministratif = Object.assign({}, tabAdministratif, {
      dateEntree: tabAdministratif.dateEntree && tabAdministratif.dateEntree.isValid() ? tabAdministratif.dateEntree.toJSON() : undefined,
      dateSortie: tabAdministratif.dateSortie && tabAdministratif.dateSortie.isValid() ? tabAdministratif.dateSortie.toJSON() : undefined,
      datecreation:
        tabAdministratif.datecreation && tabAdministratif.datecreation.isValid() ? tabAdministratif.datecreation.toJSON() : undefined,
      datemodif: tabAdministratif.datemodif && tabAdministratif.datemodif.isValid() ? tabAdministratif.datemodif.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateEntree = res.body.dateEntree ? moment(res.body.dateEntree) : undefined;
      res.body.dateSortie = res.body.dateSortie ? moment(res.body.dateSortie) : undefined;
      res.body.datecreation = res.body.datecreation ? moment(res.body.datecreation) : undefined;
      res.body.datemodif = res.body.datemodif ? moment(res.body.datemodif) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabAdministratif: ITabAdministratif) => {
        tabAdministratif.dateEntree = tabAdministratif.dateEntree ? moment(tabAdministratif.dateEntree) : undefined;
        tabAdministratif.dateSortie = tabAdministratif.dateSortie ? moment(tabAdministratif.dateSortie) : undefined;
        tabAdministratif.datecreation = tabAdministratif.datecreation ? moment(tabAdministratif.datecreation) : undefined;
        tabAdministratif.datemodif = tabAdministratif.datemodif ? moment(tabAdministratif.datemodif) : undefined;
      });
    }
    return res;
  }
}
