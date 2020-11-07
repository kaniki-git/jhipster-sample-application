import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';

export interface ITabUser {
  id?: number;
  login?: string;
  mdp?: string;
  nom?: string;
  prenom?: string;
  dateNaissance?: string;
  genre?: string;
  sexe?: string;
  telephone?: string;
  email?: string;
  estActif?: boolean;
  dmajMdp?: string;
  staConnex?: boolean;
  matriculeCreation?: string;
  matriculeModif?: string;
  dateCreation?: string;
  dateModif?: string;
  langue?: string;
  tabPersonnel?: ITabPersonnel;
}

export class TabUser implements ITabUser {
  constructor(
    public id?: number,
    public login?: string,
    public mdp?: string,
    public nom?: string,
    public prenom?: string,
    public dateNaissance?: string,
    public genre?: string,
    public sexe?: string,
    public telephone?: string,
    public email?: string,
    public estActif?: boolean,
    public dmajMdp?: string,
    public staConnex?: boolean,
    public matriculeCreation?: string,
    public matriculeModif?: string,
    public dateCreation?: string,
    public dateModif?: string,
    public langue?: string,
    public tabPersonnel?: ITabPersonnel
  ) {
    this.estActif = this.estActif || false;
    this.staConnex = this.staConnex || false;
  }
}
