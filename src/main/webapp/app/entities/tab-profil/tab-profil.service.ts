import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabProfil } from 'app/shared/model/tab-profil.model';

type EntityResponseType = HttpResponse<ITabProfil>;
type EntityArrayResponseType = HttpResponse<ITabProfil[]>;

@Injectable({ providedIn: 'root' })
export class TabProfilService {
  public resourceUrl = SERVER_API_URL + 'api/tab-profils';

  constructor(protected http: HttpClient) {}

  create(tabProfil: ITabProfil): Observable<EntityResponseType> {
    return this.http.post<ITabProfil>(this.resourceUrl, tabProfil, { observe: 'response' });
  }

  update(tabProfil: ITabProfil): Observable<EntityResponseType> {
    return this.http.put<ITabProfil>(this.resourceUrl, tabProfil, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITabProfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITabProfil[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
