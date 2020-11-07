import { Moment } from 'moment';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { ITabSpecialite } from 'app/shared/model/tab-specialite.model';
import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';

export interface ITabPersonnel {
  id?: number;
  matricule?: string;
  etatCivil?: string;
  typePersonnel?: string;
  activite?: string;
  grade?: string;
  dateentreeservice?: Moment;
  nomstatut?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
  tabPatient?: ITabPatient;
  tabCoordonnee?: ITabCoordonnee;
  tabSpecialite?: ITabSpecialite;
  tabAccouchements?: ITabAccouchement[];
}

export class TabPersonnel implements ITabPersonnel {
  constructor(
    public id?: number,
    public matricule?: string,
    public etatCivil?: string,
    public typePersonnel?: string,
    public activite?: string,
    public grade?: string,
    public dateentreeservice?: Moment,
    public nomstatut?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment,
    public tabPatient?: ITabPatient,
    public tabCoordonnee?: ITabCoordonnee,
    public tabSpecialite?: ITabSpecialite,
    public tabAccouchements?: ITabAccouchement[]
  ) {}
}
