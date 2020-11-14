import { Moment } from 'moment';

export interface ITabCoordonnee {
  id?: number;
  quartier?: string;
  commune?: string;
  ville?: string;
  codeVille?: string;
  rue?: string;
  numero?: number;
  telephone?: string;
  portable?: string;
  prevenir?: string;
  fax?: string;
  adresseMail?: string;
  adressePrevenir?: string;
  adresseLibelleLong?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
}

export class TabCoordonnee implements ITabCoordonnee {
  constructor(
    public id?: number,
    public quartier?: string,
    public commune?: string,
    public ville?: string,
    public codeVille?: string,
    public rue?: string,
    public numero?: number,
    public telephone?: string,
    public portable?: string,
    public prevenir?: string,
    public fax?: string,
    public adresseMail?: string,
    public adressePrevenir?: string,
    public adresseLibelleLong?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment
  ) {}
}
