import { Moment } from 'moment';

export interface ITabHistoriquePatient {
  id?: number;
  numeroDossier?: string;
  nom?: string;
  prenom?: string;
  matriculeUtilisateur?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
}

export class TabHistoriquePatient implements ITabHistoriquePatient {
  constructor(
    public id?: number,
    public numeroDossier?: string,
    public nom?: string,
    public prenom?: string,
    public matriculeUtilisateur?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment
  ) {}
}
