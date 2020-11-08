import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
    return this.http.post<ITabUser>(this.resourceUrl, tabUser, { observe: 'response' });
  }

  update(tabUser: ITabUser): Observable<EntityResponseType> {
    return this.http.put<ITabUser>(this.resourceUrl, tabUser, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITabUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITabUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
