import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabRendezvous } from 'app/shared/model/tab-rendezvous.model';

type EntityResponseType = HttpResponse<ITabRendezvous>;
type EntityArrayResponseType = HttpResponse<ITabRendezvous[]>;

@Injectable({ providedIn: 'root' })
export class TabRendezvousService {
  public resourceUrl = SERVER_API_URL + 'api/tab-rendezvous';

  constructor(protected http: HttpClient) {}

  create(tabRendezvous: ITabRendezvous): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabRendezvous);
    return this.http
      .post<ITabRendezvous>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabRendezvous: ITabRendezvous): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabRendezvous);
    return this.http
      .put<ITabRendezvous>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabRendezvous>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabRendezvous[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabRendezvous: ITabRendezvous): ITabRendezvous {
    const copy: ITabRendezvous = Object.assign({}, tabRendezvous, {
      dateDebut: tabRendezvous.dateDebut && tabRendezvous.dateDebut.isValid() ? tabRendezvous.dateDebut.toJSON() : undefined,
      dateFin: tabRendezvous.dateFin && tabRendezvous.dateFin.isValid() ? tabRendezvous.dateFin.toJSON() : undefined,
      datecreation: tabRendezvous.datecreation && tabRendezvous.datecreation.isValid() ? tabRendezvous.datecreation.toJSON() : undefined,
      datemodif: tabRendezvous.datemodif && tabRendezvous.datemodif.isValid() ? tabRendezvous.datemodif.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDebut = res.body.dateDebut ? moment(res.body.dateDebut) : undefined;
      res.body.dateFin = res.body.dateFin ? moment(res.body.dateFin) : undefined;
      res.body.datecreation = res.body.datecreation ? moment(res.body.datecreation) : undefined;
      res.body.datemodif = res.body.datemodif ? moment(res.body.datemodif) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabRendezvous: ITabRendezvous) => {
        tabRendezvous.dateDebut = tabRendezvous.dateDebut ? moment(tabRendezvous.dateDebut) : undefined;
        tabRendezvous.dateFin = tabRendezvous.dateFin ? moment(tabRendezvous.dateFin) : undefined;
        tabRendezvous.datecreation = tabRendezvous.datecreation ? moment(tabRendezvous.datecreation) : undefined;
        tabRendezvous.datemodif = tabRendezvous.datemodif ? moment(tabRendezvous.datemodif) : undefined;
      });
    }
    return res;
  }
}
