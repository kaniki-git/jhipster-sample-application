import { Moment } from 'moment';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';

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
  tabConsultation?: ITabConsultation;
  tabGynecologie?: ITabGynecologie;
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
    public tabConsultation?: ITabConsultation,
    public tabGynecologie?: ITabGynecologie
  ) {}
}
