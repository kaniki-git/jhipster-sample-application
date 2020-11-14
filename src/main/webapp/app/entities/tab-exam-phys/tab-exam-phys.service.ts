import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabExamPhys } from 'app/shared/model/tab-exam-phys.model';

type EntityResponseType = HttpResponse<ITabExamPhys>;
type EntityArrayResponseType = HttpResponse<ITabExamPhys[]>;

@Injectable({ providedIn: 'root' })
export class TabExamPhysService {
  public resourceUrl = SERVER_API_URL + 'api/tab-exam-phys';

  constructor(protected http: HttpClient) {}

  create(tabExamPhys: ITabExamPhys): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabExamPhys);
    return this.http
      .post<ITabExamPhys>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabExamPhys: ITabExamPhys): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabExamPhys);
    return this.http
      .put<ITabExamPhys>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabExamPhys>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabExamPhys[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabExamPhys: ITabExamPhys): ITabExamPhys {
    const copy: ITabExamPhys = Object.assign({}, tabExamPhys, {
      dateExamPhys: tabExamPhys.dateExamPhys && tabExamPhys.dateExamPhys.isValid() ? tabExamPhys.dateExamPhys.toJSON() : undefined,
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
      res.body.forEach((tabExamPhys: ITabExamPhys) => {
        tabExamPhys.dateExamPhys = tabExamPhys.dateExamPhys ? moment(tabExamPhys.dateExamPhys) : undefined;
      });
    }
    return res;
  }
}
