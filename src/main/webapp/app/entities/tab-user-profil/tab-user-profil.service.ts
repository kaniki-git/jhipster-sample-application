import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

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
    const copy = this.convertDateFromClient(tabUserProfil);
    return this.http
      .post<ITabUserProfil>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tabUserProfil: ITabUserProfil): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tabUserProfil);
    return this.http
      .put<ITabUserProfil>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITabUserProfil>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITabUserProfil[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tabUserProfil: ITabUserProfil): ITabUserProfil {
    const copy: ITabUserProfil = Object.assign({}, tabUserProfil, {
      dateModif: tabUserProfil.dateModif && tabUserProfil.dateModif.isValid() ? tabUserProfil.dateModif.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateModif = res.body.dateModif ? moment(res.body.dateModif) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tabUserProfil: ITabUserProfil) => {
        tabUserProfil.dateModif = tabUserProfil.dateModif ? moment(tabUserProfil.dateModif) : undefined;
      });
    }
    return res;
  }
}
