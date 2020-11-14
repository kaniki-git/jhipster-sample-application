import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITabUserProfil } from 'app/shared/model/tab-user-profil.model';

type EntityResponseType = HttpResponse<ITabUserProfil>;
type EntityArrayResponseType = HttpResponse<ITabUserProfil[]>;

@Injectable({ providedIn: 'root' })
export class TabUserProfilService {
  public resourceUrl = SERVER_API_URL + 'api/tab-user-profils';

  constructor(protected http: HttpClient) {}

  create(tabUserProfil: ITabUserProfil): Observable<EntityResponseType> {
    return this.http.post<ITabUserProfil>(this.resourceUrl, tabUserProfil, { observe: 'response' });
  }

  update(tabUserProfil: ITabUserProfil): Observable<EntityResponseType> {
    return this.http.put<ITabUserProfil>(this.resourceUrl, tabUserProfil, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITabUserProfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITabUserProfil[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
