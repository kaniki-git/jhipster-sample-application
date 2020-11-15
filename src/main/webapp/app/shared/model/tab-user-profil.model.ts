import { Moment } from 'moment';
import { ITabUser } from 'app/shared/model/tab-user.model';
import { ITabProfil } from 'app/shared/model/tab-profil.model';

export interface ITabUserProfil {
  id?: number;
  usrId?: number;
  profileID?: number;
  estActif?: boolean;
  matriculeCreation?: string;
  dateCreation?: string;
  matriculeModif?: string;
  dateModif?: Moment;
  tabUser?: ITabUser;
  tabProfil?: ITabProfil;
}

export class TabUserProfil implements ITabUserProfil {
  constructor(
    public id?: number,
    public usrId?: number,
    public profileID?: number,
    public estActif?: boolean,
    public matriculeCreation?: string,
    public dateCreation?: string,
    public matriculeModif?: string,
    public dateModif?: Moment,
    public tabUser?: ITabUser,
    public tabProfil?: ITabProfil
  ) {
    this.estActif = this.estActif || false;
  }
}
