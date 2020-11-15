import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabRefPays } from 'app/shared/model/tab-ref-pays.model';

type EntityResponseType = HttpResponse<ITabRefPays>;
type EntityArrayResponseType = HttpResponse<ITabRefPays[]>;

@Injectable({ providedIn: 'root' })
export class TabRefPaysService {
  public resourceUrl = SERVER_API_URL + 'api/tab-ref-pays';

  constructor(protected http: HttpClient) {}

  create(tabRefPays: ITabRefPays): Observable<EntityResponseType> {
    return this.http.post<ITabRefPays>(this.resourceUrl, tabRefPays, { observe: 'response' });
  }

  update(tabRefPays: ITabRefPays): Observable<EntityResponseType> {
    return this.http.put<ITabRefPays>(this.resourceUrl, tabRefPays, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITabRefPays>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITabRefPays[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
