import { Moment } from 'moment';

export interface ITabHistoriquePatient {
  id?: number;
  numeroDossier?: string;
  matriculePersonnel?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
}

export class TabHistoriquePatient implements ITabHistoriquePatient {
  constructor(
    public id?: number,
    public numeroDossier?: string,
    public matriculePersonnel?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment
  ) {}
}
