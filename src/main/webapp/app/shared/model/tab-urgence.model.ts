import { Moment } from 'moment';
import { ITabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';

export interface ITabUrgence {
  id?: number;
  dateArriveeUrgence?: Moment;
  dateDepartUrgence?: Moment;
  rapportUrgence?: string;
  tabParamVitauxes?: ITabParamVitaux[];
  tabConsultations?: ITabConsultation[];
}

export class TabUrgence implements ITabUrgence {
  constructor(
    public id?: number,
    public dateArriveeUrgence?: Moment,
    public dateDepartUrgence?: Moment,
    public rapportUrgence?: string,
    public tabParamVitauxes?: ITabParamVitaux[],
    public tabConsultations?: ITabConsultation[]
  ) {}
}
