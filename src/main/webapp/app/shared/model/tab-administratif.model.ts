import { Moment } from 'moment';
import { ITabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

export interface ITabAdministratif {
  id?: number;
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
}

export class TabAdministratif implements ITabAdministratif {
  constructor(
    public id?: number,
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
    public tabCoordonnee?: ITabCoordonnee
  ) {}
}
