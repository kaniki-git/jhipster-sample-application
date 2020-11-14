import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';

type EntityResponseType = HttpResponse<ITabGynecologie>;
type EntityArrayResponseType = HttpResponse<ITabGynecologie[]>;

@Injectable({ providedIn: 'root' })
export class TabGynecologieService {
  public resourceUrl = SERVER_API_URL + 'api/tab-gynecologies';

  constructor(protected http: HttpClient) {}

  create(tabGynecologie: ITabGynecologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabGynecologie);
    return this.http
      .post<ITabGynecologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabGynecologie: ITabGynecologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabGynecologie);
    return this.http
      .put<ITabGynecologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabGynecologie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabGynecologie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabGynecologie: ITabGynecologie): ITabGynecologie {
    const copy: ITabGynecologie = Object.assign({}, tabGynecologie, {
      dateConsult: tabGynecologie.dateConsult && tabGynecologie.dateConsult.isValid() ? tabGynecologie.dateConsult.toJSON() : undefined,
      dateOvulation:
        tabGynecologie.dateOvulation && tabGynecologie.dateOvulation.isValid() ? tabGynecologie.dateOvulation.toJSON() : undefined,
      dateFin: tabGynecologie.dateFin && tabGynecologie.dateFin.isValid() ? tabGynecologie.dateFin.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateConsult = res.body.dateConsult ? moment(res.body.dateConsult) : undefined;
      res.body.dateOvulation = res.body.dateOvulation ? moment(res.body.dateOvulation) : undefined;
      res.body.dateFin = res.body.dateFin ? moment(res.body.dateFin) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabGynecologie: ITabGynecologie) => {
        tabGynecologie.dateConsult = tabGynecologie.dateConsult ? moment(tabGynecologie.dateConsult) : undefined;
        tabGynecologie.dateOvulation = tabGynecologie.dateOvulation ? moment(tabGynecologie.dateOvulation) : undefined;
        tabGynecologie.dateFin = tabGynecologie.dateFin ? moment(tabGynecologie.dateFin) : undefined;
      });
    }
    return res;
  }
}
