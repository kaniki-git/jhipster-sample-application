import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabUser } from 'app/shared/model/tab-user.model';

type EntityResponseType = HttpResponse<ITabUser>;
type EntityArrayResponseType = HttpResponse<ITabUser[]>;

@Injectable({ providedIn: 'root' })
export class TabUserService {
  public resourceUrl = SERVER_API_URL + 'api/tab-users';

  constructor(protected http: HttpClient) {}

  create(tabUser: ITabUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabUser);
    return this.http
      .post<ITabUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabUser: ITabUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabUser);
    return this.http
      .put<ITabUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabUser[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabUser: ITabUser): ITabUser {
    const copy: ITabUser = Object.assign({}, tabUser, {
      resetDate: tabUser.resetDate && tabUser.resetDate.isValid() ? tabUser.resetDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.resetDate = res.body.resetDate ? moment(res.body.resetDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabUser: ITabUser) => {
        tabUser.resetDate = tabUser.resetDate ? moment(tabUser.resetDate) : undefined;
      });
    }
    return res;
  }
}
