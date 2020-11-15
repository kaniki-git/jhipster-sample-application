import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabBiologie } from 'app/shared/model/tab-biologie.model';

type EntityResponseType = HttpResponse<ITabBiologie>;
type EntityArrayResponseType = HttpResponse<ITabBiologie[]>;

@Injectable({ providedIn: 'root' })
export class TabBiologieService {
  public resourceUrl = SERVER_API_URL + 'api/tab-biologies';

  constructor(protected http: HttpClient) {}

  create(tabBiologie: ITabBiologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabBiologie);
    return this.http
      .post<ITabBiologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabBiologie: ITabBiologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabBiologie);
    return this.http
      .put<ITabBiologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabBiologie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabBiologie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabBiologie: ITabBiologie): ITabBiologie {
    const copy: ITabBiologie = Object.assign({}, tabBiologie, {
      dateBiologie: tabBiologie.dateBiologie && tabBiologie.dateBiologie.isValid() ? tabBiologie.dateBiologie.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateBiologie = res.body.dateBiologie ? moment(res.body.dateBiologie) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabBiologie: ITabBiologie) => {
        tabBiologie.dateBiologie = tabBiologie.dateBiologie ? moment(tabBiologie.dateBiologie) : undefined;
      });
    }
    return res;
  }
}
