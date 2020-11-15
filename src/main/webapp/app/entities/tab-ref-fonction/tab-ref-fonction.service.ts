import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabRefFonction } from 'app/shared/model/tab-ref-fonction.model';

type EntityResponseType = HttpResponse<ITabRefFonction>;
type EntityArrayResponseType = HttpResponse<ITabRefFonction[]>;

@Injectable({ providedIn: 'root' })
export class TabRefFonctionService {
  public resourceUrl = SERVER_API_URL + 'api/tab-ref-fonctions';

  constructor(protected http: HttpClient) {}

  create(tabRefFonction: ITabRefFonction): Observable<EntityResponseType> {
    return this.http.post<ITabRefFonction>(this.resourceUrl, tabRefFonction, { observe: 'response' });
  }

  update(tabRefFonction: ITabRefFonction): Observable<EntityResponseType> {
    return this.http.put<ITabRefFonction>(this.resourceUrl, tabRefFonction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITabRefFonction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITabRefFonction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
