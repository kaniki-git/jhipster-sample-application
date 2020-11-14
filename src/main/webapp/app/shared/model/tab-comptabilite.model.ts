import { ITabPatient } from 'app/shared/model/tab-patient.model';

export interface ITabComptabilite {
  id?: number;
  nomAppareil?: string;
  tarifAppareil?: number;
  tarifSpecialite?: number;
  tarifConsultation?: number;
  tarifChambre?: number;
  tarifSejour?: number;
  facturePatient?: number;
  recette?: number;
  tabPatient?: ITabPatient;
}

export class TabComptabilite implements ITabComptabilite {
  constructor(
    public id?: number,
    public nomAppareil?: string,
    public tarifAppareil?: number,
    public tarifSpecialite?: number,
    public tarifConsultation?: number,
    public tarifChambre?: number,
    public tarifSejour?: number,
    public facturePatient?: number,
    public recette?: number,
    public tabPatient?: ITabPatient
  ) {}
}
