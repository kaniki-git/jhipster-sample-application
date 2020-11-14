import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabComptabilite } from 'app/shared/model/tab-comptabilite.model';

type EntityResponseType = HttpResponse<ITabComptabilite>;
type EntityArrayResponseType = HttpResponse<ITabComptabilite[]>;

@Injectable({ providedIn: 'root' })
export class TabComptabiliteService {
  public resourceUrl = SERVER_API_URL + 'api/tab-comptabilites';

  constructor(protected http: HttpClient) {}

  create(tabComptabilite: ITabComptabilite): Observable<EntityResponseType> {
    return this.http.post<ITabComptabilite>(this.resourceUrl, tabComptabilite, { observe: 'response' });
  }

  update(tabComptabilite: ITabComptabilite): Observable<EntityResponseType> {
    return this.http.put<ITabComptabilite>(this.resourceUrl, tabComptabilite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITabComptabilite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITabComptabilite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
