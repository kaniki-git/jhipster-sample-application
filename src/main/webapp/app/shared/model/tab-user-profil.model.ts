import { ITabUser } from 'app/shared/model/tab-user.model';
import { ITabProfil } from 'app/shared/model/tab-profil.model';

export interface ITabUserProfil {
  id?: number;
  estActif?: boolean;
  matriculeCreation?: string;
  dateCreation?: string;
  matriculeModif?: string;
  dateModif?: string;
  tabUser?: ITabUser;
  tabProfil?: ITabProfil;
}

export class TabUserProfil implements ITabUserProfil {
  constructor(
    public id?: number,
    public estActif?: boolean,
    public matriculeCreation?: string,
    public dateCreation?: string,
    public matriculeModif?: string,
    public dateModif?: string,
    public tabUser?: ITabUser,
    public tabProfil?: ITabProfil
  ) {
    this.estActif = this.estActif || false;
  }
}
