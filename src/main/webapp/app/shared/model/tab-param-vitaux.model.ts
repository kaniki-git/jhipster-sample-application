import { Moment } from 'moment';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';

export interface ITabParamVitaux {
  id?: number;
  dateExam?: Moment;
  ta1?: number;
  ta2?: number;
  pouls?: number;
  temperature?: number;
  frequence?: number;
  taille?: number;
  sa02?: number;
  sous?: number;
  poids?: number;
  examenPhysique?: string;
  examenCompl?: string;
  evolution?: string;
  tabConsultation?: ITabConsultation;
  tabUrgence?: ITabUrgence;
  tabHospital?: ITabHospital;
}

export class TabParamVitaux implements ITabParamVitaux {
  constructor(
    public id?: number,
    public dateExam?: Moment,
    public ta1?: number,
    public ta2?: number,
    public pouls?: number,
    public temperature?: number,
    public frequence?: number,
    public taille?: number,
    public sa02?: number,
    public sous?: number,
    public poids?: number,
    public examenPhysique?: string,
    public examenCompl?: string,
    public evolution?: string,
    public tabConsultation?: ITabConsultation,
    public tabUrgence?: ITabUrgence,
    public tabHospital?: ITabHospital
  ) {}
}
