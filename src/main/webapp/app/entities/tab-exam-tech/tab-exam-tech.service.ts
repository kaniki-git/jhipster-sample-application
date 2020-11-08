import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabExamTech } from 'app/shared/model/tab-exam-tech.model';

type EntityResponseType = HttpResponse<ITabExamTech>;
type EntityArrayResponseType = HttpResponse<ITabExamTech[]>;

@Injectable({ providedIn: 'root' })
export class TabExamTechService {
  public resourceUrl = SERVER_API_URL + 'api/tab-exam-teches';

  constructor(protected http: HttpClient) {}

  create(tabExamTech: ITabExamTech): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabExamTech);
    return this.http
      .post<ITabExamTech>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabExamTech: ITabExamTech): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabExamTech);
    return this.http
      .put<ITabExamTech>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabExamTech>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabExamTech[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabExamTech: ITabExamTech): ITabExamTech {
    const copy: ITabExamTech = Object.assign({}, tabExamTech, {
      dateExamTech: tabExamTech.dateExamTech && tabExamTech.dateExamTech.isValid() ? tabExamTech.dateExamTech.toJSON() : undefined,
      tarifExamTech: tabExamTech.tarifExamTech && tabExamTech.tarifExamTech.isValid() ? tabExamTech.tarifExamTech.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateExamTech = res.body.dateExamTech ? moment(res.body.dateExamTech) : undefined;
      res.body.tarifExamTech = res.body.tarifExamTech ? moment(res.body.tarifExamTech) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabExamTech: ITabExamTech) => {
        tabExamTech.dateExamTech = tabExamTech.dateExamTech ? moment(tabExamTech.dateExamTech) : undefined;
        tabExamTech.tarifExamTech = tabExamTech.tarifExamTech ? moment(tabExamTech.tarifExamTech) : undefined;
      });
    }
    return res;
  }
}
