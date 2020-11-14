import { Moment } from 'moment';
import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';
import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';

export interface ITabAdministratif {
  id?: number;
  provenancePatient?: ProvenancePatient;
  numeroChambre?: string;
  batiment?: string;
  dateEntree?: Moment;
  dateSortie?: Moment;
  commentaire?: string;
  couverture?: string;
  numerosecu?: string;
  nomassurrance?: string;
  coordonneesecu?: string;
  nomMedecinPerso?: string;
  matriculecreation?: string;
  datecreation?: Moment;
  matriculemodif?: string;
  datemodif?: Moment;
  tabCoordonnee?: ITabCoordonnee;
  tabUrgences?: ITabUrgence[];
}

export class TabAdministratif implements ITabAdministratif {
  constructor(
    public id?: number,
    public provenancePatient?: ProvenancePatient,
    public numeroChambre?: string,
    public batiment?: string,
    public dateEntree?: Moment,
    public dateSortie?: Moment,
    public commentaire?: string,
    public couverture?: string,
    public numerosecu?: string,
    public nomassurrance?: string,
    public coordonneesecu?: string,
    public nomMedecinPerso?: string,
    public matriculecreation?: string,
    public datecreation?: Moment,
    public matriculemodif?: string,
    public datemodif?: Moment,
    public tabCoordonnee?: ITabCoordonnee,
    public tabUrgences?: ITabUrgence[]
  ) {}
}
