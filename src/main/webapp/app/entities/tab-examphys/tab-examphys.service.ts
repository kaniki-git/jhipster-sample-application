import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabExamphys } from 'app/shared/model/tab-examphys.model';

type EntityResponseType = HttpResponse<ITabExamphys>;
type EntityArrayResponseType = HttpResponse<ITabExamphys[]>;

@Injectable({ providedIn: 'root' })
export class TabExamphysService {
  public resourceUrl = SERVER_API_URL + 'api/tab-examphys';

  constructor(protected http: HttpClient) {}

  create(tabExamphys: ITabExamphys): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabExamphys);
    return this.http
      .post<ITabExamphys>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabExamphys: ITabExamphys): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabExamphys);
    return this.http
      .put<ITabExamphys>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabExamphys>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabExamphys[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabExamphys: ITabExamphys): ITabExamphys {
    const copy: ITabExamphys = Object.assign({}, tabExamphys, {
      dateExamPhys: tabExamphys.dateExamPhys && tabExamphys.dateExamPhys.isValid() ? tabExamphys.dateExamPhys.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateExamPhys = res.body.dateExamPhys ? moment(res.body.dateExamPhys) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabExamphys: ITabExamphys) => {
        tabExamphys.dateExamPhys = tabExamphys.dateExamPhys ? moment(tabExamphys.dateExamPhys) : undefined;
      });
    }
    return res;
  }
}
