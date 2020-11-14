import { Moment } from 'moment';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';

export interface ITabRendezvous {
  id?: number;
  titre?: string;
  ville?: string;
  dateDebut?: Moment;
  dateFin?: Moment;
  jourEntier?: boolean;
  groupId?: string;
  arrierePlanCouleur?: string;
  texteCouleur?: string;
  salle?: string;
  commentaire?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
  tabPatient?: ITabPatient;
  tabPersonnel?: ITabPersonnel;
}

export class TabRendezvous implements ITabRendezvous {
  constructor(
    public id?: number,
    public titre?: string,
    public ville?: string,
    public dateDebut?: Moment,
    public dateFin?: Moment,
    public jourEntier?: boolean,
    public groupId?: string,
    public arrierePlanCouleur?: string,
    public texteCouleur?: string,
    public salle?: string,
    public commentaire?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment,
    public tabPatient?: ITabPatient,
    public tabPersonnel?: ITabPersonnel
  ) {
    this.jourEntier = this.jourEntier || false;
  }
}
