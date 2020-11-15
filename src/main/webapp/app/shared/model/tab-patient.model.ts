import { Moment } from 'moment';
import { ITabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';
import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';
import { ITabComptabilite } from 'app/shared/model/tab-comptabilite.model';

export interface ITabPatient {
  id?: number;
  matricule?: string;
  sexe?: string;
  etatCivil?: string;
  nombreEnfant?: number;
  nombreGrossesse?: string;
  nom?: string;
  prenom1?: string;
  prenom2?: string;
  datenaissance?: Moment;
  lieunaissance?: string;
  nationalite?: string;
  activite?: string;
  photoUrl?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
  tabHistoriquePatient?: ITabHistoriquePatient;
  tabAdministratifs?: ITabAdministratif[];
  tabComptabilites?: ITabComptabilite[];
}

export class TabPatient implements ITabPatient {
  constructor(
    public id?: number,
    public matricule?: string,
    public sexe?: string,
    public etatCivil?: string,
    public nombreEnfant?: number,
    public nombreGrossesse?: string,
    public nom?: string,
    public prenom1?: string,
    public prenom2?: string,
    public datenaissance?: Moment,
    public lieunaissance?: string,
    public nationalite?: string,
    public activite?: string,
    public photoUrl?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment,
    public tabHistoriquePatient?: ITabHistoriquePatient,
    public tabAdministratifs?: ITabAdministratif[],
    public tabComptabilites?: ITabComptabilite[]
  ) {}
}
